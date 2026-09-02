package com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto

import com.sphirye.shared.web.annotation.EntityExists
import com.sphirye.springtemplate.domain.model.Penalty
import jakarta.validation.constraints.NotNull

data class PenaltyRequest(
    @field:EntityExists(entityName = "MatchScore", primaryKey = "id")
    val scoreId: Long? = null,
    val title: String? = null,
    val type: Penalty.PenaltyType? = null,
    @field:NotNull
    val value: Int? = null,
) {
    fun toDomain(): Penalty = Penalty(
        scoreId = scoreId,
        title = title,
        type = type,
        value = value,
    )
}

class PenaltyFilter(
    var scoreId: Long? = null,
    var title: String? = null,
    var type: Penalty.PenaltyType? = null,
    var value: Int? = null,
) {
    fun toDomain(): Penalty = Penalty(
        scoreId = scoreId,
        title = title,
        type = type,
        value = value,
    )
}
