package com.sphirye.springtemplate.domain.model

data class PenaltyRecords(
    val id: Long? = null,
    val scoreId: Long? = null,
    val penaltyId: Long? = null,
    val fencerId: Long? = null,
    val penalty: Penalty? = null,
    val fencer: Fencer? = null,
)
