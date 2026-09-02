package com.sphirye.springtemplate.domain.model

import java.time.LocalDateTime

data class Fencer(
    val id: Long? = null,
    val name: String? = null,
    val email: String? = null,
    val createdDate: LocalDateTime? = null,
    val lastModifiedDate: LocalDateTime? = null,
    val createdById: Long? = null,
    val lastModifiedBy: Long? = null,
)
