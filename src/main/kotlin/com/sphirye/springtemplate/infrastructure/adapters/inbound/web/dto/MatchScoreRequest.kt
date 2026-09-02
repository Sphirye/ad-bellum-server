package com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto

import com.sphirye.shared.web.annotation.EntityExists
import com.sphirye.springtemplate.domain.model.MatchScore
import jakarta.validation.constraints.NotNull

data class MatchScoreRequest(
    @field:EntityExists(entityName = "Fencer", primaryKey = "id")
    val scorerId: Long? = null,

    @field:EntityExists(entityName = "ScoreAction", primaryKey = "id")
    val actionId: Long? = null,

    @field:NotNull
    val verdict: MatchScore.Verdict? = null,

    @field:NotNull
    @field:EntityExists(entityName = "Match", primaryKey = "id")
    val matchId: Long? = null,

    val afterblow: Boolean? = null,
    val control: Boolean? = null,
    val region: MatchScore.RegionType? = null,
    val penaltyRecords: List<PenaltyRecordRequest> = emptyList(),
) {
    fun toDomain(): MatchScore = MatchScore(
        scorerId = scorerId,
        actionId = actionId,
        verdict = verdict,
        matchId = matchId,
        afterblow = afterblow,
        control = control,
        region = region,
        penaltyRecords = penaltyRecords.map { it.toDomain() },
    )
}

class MatchScoreFilter(
    var scorerId: Long? = null,
    var actionId: Long? = null,
    var verdict: MatchScore.Verdict? = null,
    var matchId: Long? = null,
    var afterblow: Boolean? = null,
    var control: Boolean? = null,
    var region: MatchScore.RegionType? = null,
) {
    fun toDomain(): MatchScore = MatchScore(
        scorerId = scorerId,
        actionId = actionId,
        verdict = verdict,
        matchId = matchId,
        afterblow = afterblow,
        control = control,
        region = region,
    )
}
