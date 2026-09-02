package com.sphirye.springtemplate.domain.model

import java.time.LocalDateTime

data class Group(
    val id: Long? = null,
    val title: String? = null,
    val createdDate: LocalDateTime? = null,
    val lastModifiedDate: LocalDateTime? = null,
    val createdById: Long? = null,
    val lastModifiedBy: Long? = null,
)
