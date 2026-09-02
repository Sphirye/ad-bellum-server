# Arquitectura Hexagonal en Ad Bellum Server

Explicación guiada de qué es la arquitectura hexagonal y cómo se aplicó a este proyecto, usando los cambios reales del refactor como material de estudio.

---

## 1. El problema que resuelve (antes del refactor)

Antes, el proyecto era una "aplicación de una sola pieza" donde cada clase mezclaba varios trabajos. Por ejemplo, la entidad `Fencer` era tres cosas a la vez:

```kotlin
@Entity
@Table(name = "fencers")          // habla de la base de datos
class Fencer(
    @Id @GeneratedValue           // habla de la base de datos
    override var id: Long? = null,
    @field:NotNull                // habla de la validación de la API
    var name: String? = null,
)
```

- La idea de "qué es un esgrimista" (**dominio**).
- "Cómo se guarda en una tabla" (**persistencia**).
- "Qué formato tiene cuando lo recibo por JSON" (**contrato de API**).

Y los servicios estaban igual de mezclados:

```kotlin
class FencerService(...) : BaseService<Fencer, Long>(repository) // atado a JpaRepository
```

`BaseService` hablaba directamente con Spring Data. Si cambiabas de base de datos, tocabas el negocio. Para testear reglas como *"un match terminado no se puede modificar"* necesitabas levantar Spring + una base de datos entera.

**Pregunta que responde la arquitectura hexagonal:** ¿cómo separo *lo que mi aplicación es* (reglas de negocio) de *las tecnologías con las que habla* (HTTP, JPA, JWT, BD)?

---

## 2. La metáfora del hexágono

Imagina un **chef en una cocina** (el núcleo de tu aplicación):

- El chef **no necesita saber** si el pedido llegó por teléfono, por una app o en persona. Le basta un contrato: *"el mozo me pasa pedidos"*.
- El chef **no necesita saber** si los ingredientes están en la nevera, en el mercado o los trae un dron. Le basta otro contrato: *"la despensa me da ingredientes"*.

En el hexágono ocurre lo mismo:

- **Centro = dominio**: la lógica pura, sin framework.
- **Costados del hexágono = puertos**: contratos (interfaces) por donde entra y sale la información.
- **Fuera del hexágono = adaptadores**: las implementaciones que SÍ conocen las tecnologías (controladores HTTP, repositorios JPA, JWT).

**La regla de oro** (regla de dependencia): *todo apunta hacia el centro, nunca hacia fuera*. El centro no importa nada de la periferia; la periferia sí importa el centro.

---

## 3. Cómo se tradujo cada cambio en el proyecto

### Cambio 1: las entidades se separaron en dos

Antes había un solo `model/Fencer.kt` con anotaciones JPA. Ahora hay dos archivos:

`domain/model/Fencer.kt` — el **modelo de dominio** (data class pura):

```kotlin
data class Fencer(
    val id: Long? = null,
    val name: String? = null,
    val email: String? = null,
    val createdDate: LocalDateTime? = null,
    ...
)
```

Sin una sola anotación de Spring, JPA o Jackson. Es "la idea de fencer" y nada más.

`infrastructure/adapters/outbound/persistence/entity/Fencer.kt` — la **entidad JPA**, que conserva `@Entity`, `@Table`, etc.

> **Regla aprendida:** "cómo se guarda en la base de datos" es un detalle de infraestructura, no del dominio. Por eso las entidades JPA viven en la carpeta `outbound/persistence` y el dominio queda limpio.

### Cambio 2: `BaseService` desapareció y nacieron los puertos

El problema de `BaseService` era recibir un `JpaRepository` concreto (una tecnología). Se sustituyó por **contratos**:

- `domain/port/inbound/` → **puertos de entrada (driving)**: lo que la aplicación *hace*. Ej: `FencerUseCase`, `MatchUseCase`.
- `domain/port/outbound/` → **puertos de salida (driven)**: lo que la aplicación *necesita* del exterior. Ej: `FencerPersistencePort`, `TokenGeneratorPort`.

Diferencia en `MatchService`:

**Antes:**

```kotlin
class MatchService(
    private val _matchRepository: MatchRepository,        // Spring Data (tecnología)
    private val _scoreProfileService: ScoreProfileService, // otro servicio concreto
): BaseService<Match, Long>(_matchRepository)
```

**Después:**

```kotlin
@Service
class MatchService(
    private val matchPersistencePort: MatchPersistencePort, // interfaz del dominio
    private val scoreProfileUseCase: ScoreProfileUseCase,   // otro caso de uso
) : MatchUseCase
```

La capa de aplicación ya **no menciona JPA ni repositorios**: solo ve interfaces que viven en el dominio. Esa es la **Inversión de Dependencias**: quien define el contrato es el centro, y la tecnología (el repositorio JPA) es la que se adapta a ese contrato.

### Cambio 3: los repositorios se convirtieron en adaptadores

`FencerPersistenceAdapter` implementa el puerto `FencerPersistencePort`:

```kotlin
@Component
@Transactional(readOnly = true)
class FencerPersistenceAdapter(
    private val fencerRepository: FencerRepository,   // Spring Data
) : FencerPersistencePort {
    override fun findById(id: Long): Fencer? =
        fencerRepository.findById(id).map { it.toDomain() }.orElse(null)
    ...
}
```

`FencerRepository` (Spring Data) es ahora un **detalle interno** del adaptador. Si mañana cambias de MariaDB a MongoDB, tocas solo la carpeta `outbound/persistence`: creas otro adaptador que implemente el mismo `FencerPersistencePort` y **el dominio no cambia ni una línea**.

Los **mappers** (`infrastructure/adapters/outbound/persistence/mapper/`) son la frontera que traduce: entidad JPA ↔ modelo de dominio.

### Cambio 4: los controllers se volvieron "adaptadores de entrada"

`FencerController` ya no sabe nada de JPA ni recibe entidades:

```kotlin
@RestController
class FencerController(private val fencerUseCase: FencerUseCase) {
    @PostMapping("/fencer")
    fun postFencer(@Validated @RequestBody fencer: FencerRequest): Fencer =
        fencerUseCase.create(fencer.toDomain())
}
```

Traduce HTTP → llamada al caso de uso. La validación (`@NotNull`, `@EntityExists`) se movió a los **DTO de entrada** (`.../web/dto/FencerRequest.kt`), porque "cómo valido un JSON que entra" es cosa del borde HTTP.

### Cambio 5: la seguridad dejó de estar "dentro"

`AuthService` antes inyectaba `AuthenticationManager`, `JwtTokenUtil`, `SessionManager` de Spring Security. Ahora el caso de uso solo conoce puertos:

```kotlin
@Service
class AuthService(
    private val userPersistencePort: UserPersistencePort,
    private val passwordEncoderPort: PasswordEncoderPort,
    private val tokenGeneratorPort: TokenGeneratorPort,
    private val currentSessionPort: CurrentSessionPort,
) : AuthUseCase
```

`JwtTokenUtil` ahora *implementa* `TokenGeneratorPort` en `outbound/security/`. El núcleo no sabe qué librería JWT se usa (jjwt), solo que existe "algo que genera tokens".

---

## 4. Recorrido completo de una petición (GET /fencer/1)

```
  HTTP GET /fencer/1
         │
         ▼
 ┌───────────────────────────────┐
 │ ADAPTADOR DE ENTRADA          │  FencerController (infrastructure/.../web)
 │ traduce HTTP → caso de uso    │
 └───────────────┬───────────────┘
                 │  llama al puerto de entrada
                 ▼
 ┌───────────────────────────────┐
 │ CASO DE USO                   │  FencerService (application/service)
 │ reglas: si no existe → 404    │
 └───────────────┬───────────────┘
                 │  usa el puerto de salida (interfaz, NO JPA)
                 ▼
 ┌───────────────────────────────┐
 │ PUERTO DE SALIDA              │  FencerPersistencePort (domain/port/outbound)
 └───────────────┬───────────────┘
                 │  el adaptador lo implementa
                 ▼
 ┌───────────────────────────────┐
 │ ADAPTADOR DE SALIDA           │  FencerPersistenceAdapter (infrastructure)
 │ repo JPA + mapper → dominio   │
 └───────────────┬───────────────┘
                 ▼
             DATABASE
```

En cada salto la dependencia **apunta hacia dentro**: el dominio nunca mira hacia fuera; los adaptadores sí miran al dominio.

---

## 5. La prueba de valor: testear sin base de datos

Antes, testear `MatchService` requería Spring + BD. Ahora `MatchServiceTest` **no levanta Spring**: se le pasa un *fake* del puerto (un mapa en memoria) y se prueba la regla de negocio:

```kotlin
class MatchServiceTest {
    @Test
    fun `update of a finished match should throw ConflictException`() {
        val store = mutableMapOf(1L to Match(..., state = Match.MatchState.FINISHED))
        val service = MatchService(FakeMatchPersistencePort(store), FakeScoreProfileUseCase())

        assertThrows(ConflictException::class.java) {
            service.update(1L, Match(...))
        }
    }
}
```

Es posible porque la regla *"no se puede modificar un match terminado"* vive en `MatchService`, que solo conoce interfaces. Esa es la gran recompensa de hexagonal: **la lógica de negocio se testea en milisegundos, sin infraestructura.**

---

## 6. Resumen

| Concepto | En el proyecto |
|---|---|
| **Núcleo** | `domain/model` (data classes puras) |
| **Puertos de entrada** | `domain/port/inbound` (`FencerUseCase`...) |
| **Puertos de salida** | `domain/port/outbound` (`FencerPersistencePort`, `TokenGeneratorPort`...) |
| **Lógica de negocio** | `application/service` (implementa use cases, solo ve interfaces) |
| **Adaptador de entrada** | controllers + DTOs en `infrastructure/adapters/inbound/web` |
| **Adaptadores de salida** | `infrastructure/adapters/outbound/{persistence,security}` |

**Qué se ganó:**
1. La API dejó de exponer entidades JPA (DTOs de entrada la protegen).
2. El dominio es puro: `Match` no tiene ni `@Entity` ni JWT.
3. Cambiar de tecnología (BD, librería JWT, framework web) solo toca la carpeta `infrastructure`.
4. Las reglas de negocio se testean sin BD (como en `MatchServiceTest`).

**Qué se pagó:** más archivos — interfaces, mappers, DTOs. Es el precio de la indirección, y se nota en proyectos que sí crecen y cambian de tecnología.

> **Frase que resume todo:** *"El dominio define los contratos; la infraestructura los cumple."* La dirección de las dependencias es la esencia: `domain ← application ← infrastructure`, nunca al revés.
