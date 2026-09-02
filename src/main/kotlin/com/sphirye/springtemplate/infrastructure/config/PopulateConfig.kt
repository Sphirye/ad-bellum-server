package com.sphirye.springtemplate.infrastructure.config

import com.sphirye.springtemplate.domain.model.Authority
import com.sphirye.springtemplate.domain.model.User
import com.sphirye.springtemplate.domain.port.inbound.UserUseCase
import com.sphirye.springtemplate.domain.port.outbound.AuthorityPersistencePort
import com.sphirye.springtemplate.domain.port.outbound.UserPersistencePort
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class PopulateConfig(
    private val authorityPersistencePort: AuthorityPersistencePort,
    private val userPersistencePort: UserPersistencePort,
    private val userUseCase: UserUseCase,
) {

    @PostConstruct
    fun init() {
        Authority.Role.values().forEach {
            if (!authorityPersistencePort.existsByRole(it)) {
                authorityPersistencePort.save(Authority(role = it))
            }
        }

        if (userPersistencePort.count() <= 0) {
            val user = User(
                username = "sphirye",
                password = "1234",
                email = "sphirye@gmail.com",
            )

            userUseCase.create(user)
        }
    }
}
