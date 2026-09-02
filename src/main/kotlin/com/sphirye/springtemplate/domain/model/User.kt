package com.sphirye.springtemplate.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val username: String? = null,
    val email: String? = null,
    @get:JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val password: String? = null,
    val authorities: MutableSet<Authority> = mutableSetOf(),
    val createdDate: LocalDateTime? = null,
    val lastModifiedDate: LocalDateTime? = null,
    val createdById: Long? = null,
    val lastModifiedBy: Long? = null,
)
