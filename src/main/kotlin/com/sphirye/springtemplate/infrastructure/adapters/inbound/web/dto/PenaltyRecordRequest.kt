package com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto

import com.sphirye.shared.web.annotation.EntityExists
import com.sphirye.springtemplate.domain.model.PenaltyRecords
import jakarta.validation.constraints.NotNull

data class PenaltyRecordRequest(
    @field:EntityExists(entityName = "MatchScore", primaryKey = "id")
    val scoreId: Long? = null,

    @field:NotNull
    @field:EntityExists(entityName = "Penalty", primaryKey = "id")
    val penaltyId: Long? = null,

    @field:NotNull
    @field:EntityExists(entityName = "Fencer", primaryKey = "id")
    val fencerId: Long? = null,
) {
    fun toDomain(): PenaltyRecords = PenaltyRecords(
        scoreId = scoreId,
        penaltyId = penaltyId,
        fencerId = fencerId,
    )
}
