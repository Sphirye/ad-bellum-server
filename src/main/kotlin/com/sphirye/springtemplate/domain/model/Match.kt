package com.sphirye.springtemplate.domain.model

import java.time.LocalDateTime

data class Match(
    val id: Long? = null,
    val fencer_1_id: Long? = null,
    val fencer_1: Fencer? = null,
    val fencer_2: Fencer? = null,
    val fencer_2_id: Long? = null,
    val state: MatchState? = null,
    val resolution: MatchResolution? = null,
    val scoreProfileId: Long? = null,
    val groupId: Long? = null,
    val winnerFencerId: Long? = null,
    val group: Group? = null,
    val scoreProfile: ScoreProfile? = null,
    val scores: List<MatchScore>? = null,
    val createdDate: LocalDateTime? = null,
    val lastModifiedDate: LocalDateTime? = null,
    val createdById: Long? = null,
    val lastModifiedBy: Long? = null,
) {
    enum class MatchState {
        WAITING, IN_PROGRESS, FINISHED
    }

    enum class MatchResolution {
        POINTS_REACHED,
        DOUBLE_OUT,
        TIME_OUT,
        WITHDRAWAL,
        DISQUALIFICATION,
        INJURY,
        OTHER,
    }
}
