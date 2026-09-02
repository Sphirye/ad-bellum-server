package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity

import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "authorities")
class Authority (

    @Id
    @GeneratedValue
    override var id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    var role: Role,

    var description: String? = null,

    @ManyToMany(mappedBy = "authorities")
    var users: MutableSet<User> = mutableSetOf(),

    @Column(nullable = false)
    var enabled: Boolean? = true,

    ): Identifiable<Long> {
    enum class Role {
        SUPER_ADMIN, ADMIN, MOD
    }
}
