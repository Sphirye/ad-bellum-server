package com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto

import com.sphirye.shared.web.annotation.EntityExists
import com.sphirye.springtemplate.domain.model.Match
import jakarta.validation.constraints.NotNull

data class MatchRequest(
    @field:NotNull
    @field:EntityExists(entityName = "Fencer", primaryKey = "id")
    val fencer_1_id: Long? = null,

    @field:NotNull
    @field:EntityExists(entityName = "Fencer", primaryKey = "id")
    val fencer_2_id: Long? = null,

    @field:NotNull
    val state: Match.MatchState? = null,

    val resolution: Match.MatchResolution? = null,

    @field:EntityExists(entityName = "ScoreProfile", primaryKey = "id")
    val scoreProfileId: Long? = null,

    val scoreProfile: ScoreProfileRequest? = null,

    @field:EntityExists(entityName = "Group", primaryKey = "id")
    val groupId: Long? = null,

    @field:EntityExists(entityName = "Fencer", primaryKey = "id")
    val winnerFencerId: Long? = null,
) {
    fun toDomain(): Match = Match(
        fencer_1_id = fencer_1_id,
        fencer_2_id = fencer_2_id,
        state = state,
        resolution = resolution,
        scoreProfileId = scoreProfileId,
        groupId = groupId,
        winnerFencerId = winnerFencerId,
        scoreProfile = scoreProfile?.toDomain(),
    )
}

data class MatchFinishRequest(
    val state: Match.MatchState? = null,
    val resolution: Match.MatchResolution? = null,
    val winnerFencerId: Long? = null,
) {
    fun toDomain(): Match = Match(
        state = state,
        resolution = resolution,
        winnerFencerId = winnerFencerId,
    )
}
