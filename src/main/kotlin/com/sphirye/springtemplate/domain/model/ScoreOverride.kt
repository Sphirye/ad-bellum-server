package com.sphirye.springtemplate.domain.model

import java.time.LocalDateTime

data class ScoreOverride(
    val id: Long? = null,
    val region: MatchScore.RegionType? = null,
    val value: Int? = null,
    val createdDate: LocalDateTime? = null,
    val lastModifiedDate: LocalDateTime? = null,
    val createdById: Long? = null,
    val lastModifiedBy: Long? = null,
)
