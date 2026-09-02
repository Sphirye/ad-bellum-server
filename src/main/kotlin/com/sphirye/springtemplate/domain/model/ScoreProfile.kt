package com.sphirye.springtemplate.domain.model

import java.time.LocalDateTime

data class ScoreProfile(
    val id: Long? = null,
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
    val type: ScoreProfileType? = null,
    val penalties: MutableList<Penalty>? = null,
    val actions: MutableList<ScoreAction>? = mutableListOf(),
    val createdDate: LocalDateTime? = null,
    val lastModifiedDate: LocalDateTime? = null,
    val createdById: Long? = null,
    val lastModifiedBy: Long? = null,
) {
    enum class ScoreProfileType {
        TEMPLATE, INSTANCE
    }

    fun withoutIds(): ScoreProfile = copy(
        id = null,
        penalties = penalties?.map { it.copy(id = null) }?.toMutableList(),
        actions = actions?.map { action ->
            action.copy(
                id = null,
                overrides = action.overrides.map { it.copy(id = null) }.toMutableList(),
            )
        }?.toMutableList(),
    )
}
