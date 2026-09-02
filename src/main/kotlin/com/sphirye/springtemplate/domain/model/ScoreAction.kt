package com.sphirye.springtemplate.domain.model

import java.time.LocalDateTime

data class ScoreAction(
    val id: Long? = null,
    val title: String? = null,
    val value: Int? = null,
    val overrides: MutableList<ScoreOverride> = mutableListOf(),
    val createdDate: LocalDateTime? = null,
    val lastModifiedDate: LocalDateTime? = null,
    val createdById: Long? = null,
    val lastModifiedBy: Long? = null,
)
