package com.sphirye.springtemplate.domain.model

import java.time.LocalDateTime

data class MatchScore(
    val id: Long? = null,
    val scorerId: Long? = null,
    val actionId: Long? = null,
    val verdict: Verdict? = null,
    val matchId: Long? = null,
    val afterblow: Boolean? = null,
    val control: Boolean? = null,
    val region: RegionType? = null,
    val scorer: Fencer? = null,
    val action: ScoreAction? = null,
    val penaltyRecords: List<PenaltyRecords> = emptyList(),
    val createdDate: LocalDateTime? = null,
    val lastModifiedDate: LocalDateTime? = null,
    val createdById: Long? = null,
    val lastModifiedBy: Long? = null,
) {
    enum class Verdict {
        POINT, DOUBLE, NO_EXCHANGE, NO_QUALITY
    }

    enum class RegionType {
        HEAD, ARM, HAND, CHEST, LEG
    }
}
