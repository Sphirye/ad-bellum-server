package com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto

import com.sphirye.springtemplate.domain.model.ScoreAction
import com.sphirye.springtemplate.domain.model.ScoreOverride

data class ScoreActionRequest(
    val title: String? = null,
    val value: Int? = null,
    val overrides: MutableList<ScoreOverrideRequest> = mutableListOf(),
) {
    fun toDomain(): ScoreAction = ScoreAction(
        title = title,
        value = value,
        overrides = overrides.map { it.toDomain() }.toMutableList(),
    )
}

data class ScoreOverrideRequest(
    val region: com.sphirye.springtemplate.domain.model.MatchScore.RegionType? = null,
    val value: Int? = null,
) {
    fun toDomain(): ScoreOverride = ScoreOverride(
        region = region,
        value = value,
    )
}
