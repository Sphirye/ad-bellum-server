package com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto

import com.sphirye.springtemplate.domain.model.MatchScore
import com.sphirye.springtemplate.domain.model.ScoreProfile
import jakarta.validation.constraints.NotNull

data class ScoreProfileRequest(
    val name: String? = null,
    val controls: Int? = null,
    val afterblow: Int? = null,
    val dobleoutLimit: Int? = null,
    val hasPointsLimit: Boolean? = false,
    val pointsLimit: Int? = null,
    val hasDobleoutLimit: Boolean? = false,
    val hasTimeLimit: Boolean? = false,
    val timeLimitInSeconds: Int? = null,
    val timeLeft: Int? = null,
    val elapsedTimeInSeconds: Int? = null,
    val countdown: Boolean? = null,
    val forcedlyCloseOnTimeout: Boolean? = null,
    val type: ScoreProfile.ScoreProfileType? = null,
    val penalties: MutableList<PenaltyRequest>? = null,
    val actions: MutableList<ScoreActionRequest>? = mutableListOf(),
) {
    fun toDomain(): ScoreProfile = ScoreProfile(
        name = name,
        controls = controls,
        afterblow = afterblow,
        dobleoutLimit = dobleoutLimit,
        hasPointsLimit = hasPointsLimit,
        pointsLimit = pointsLimit,
        hasDobleoutLimit = hasDobleoutLimit,
        hasTimeLimit = hasTimeLimit,
        timeLimitInSeconds = timeLimitInSeconds,
        timeLeft = timeLeft,
        elapsedTimeInSeconds = elapsedTimeInSeconds,
        countdown = countdown,
        forcedlyCloseOnTimeout = forcedlyCloseOnTimeout,
        type = type,
        penalties = penalties?.map { it.toDomain() }?.toMutableList(),
        actions = actions?.map { it.toDomain() }?.toMutableList(),
    )
}

class ScoreProfileFilter(
    var name: String? = null,
    var controls: Int? = null,
    var afterblow: Int? = null,
    var dobleoutLimit: Int? = null,
    var hasPointsLimit: Boolean? = false,
    var pointsLimit: Int? = null,
    var hasDobleoutLimit: Boolean? = false,
    var hasTimeLimit: Boolean? = false,
    var timeLimitInSeconds: Int? = null,
    var type: ScoreProfile.ScoreProfileType? = null,
) {
    fun toDomain(): ScoreProfile = ScoreProfile(
        name = name,
        controls = controls,
        afterblow = afterblow,
        dobleoutLimit = dobleoutLimit,
        hasPointsLimit = hasPointsLimit,
        pointsLimit = pointsLimit,
        hasDobleoutLimit = hasDobleoutLimit,
        hasTimeLimit = hasTimeLimit,
        timeLimitInSeconds = timeLimitInSeconds,
        type = type,
    )
}
