package com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto

import com.sphirye.springtemplate.domain.model.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

data class UserRequest(
    @field:NotNull
    val username: String? = null,

    @field:NotNull
    @field:Email
    val email: String? = null,

    @field:NotNull
    val password: String? = null,
) {
    fun toDomain(): User = User(
        username = username,
        email = email,
        password = password,
    )
}

class UserFilter(
    var username: String? = null,
    var email: String? = null,
) {
    fun toDomain(): User = User(
        username = username,
        email = email,
    )
}
