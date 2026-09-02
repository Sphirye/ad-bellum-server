package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.ScoreProfile
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.MatchScore as MatchScoreEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Penalty as PenaltyEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.ScoreAction as ScoreActionEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.ScoreOverride as ScoreOverrideEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.ScoreProfile as ScoreProfileEntity

fun ScoreProfileEntity.toDomain(): ScoreProfile = ScoreProfile(
    id = id,
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
    type = type?.let { ScoreProfile.ScoreProfileType.valueOf(it.name) },
    penalties = penalties?.map { it.toDomain() }?.toMutableList(),
    actions = actions?.map { it.toDomain() }?.toMutableList(),
    createdDate = createdDate,
    lastModifiedDate = lastModifiedDate,
    createdById = createdById,
    lastModifiedBy = lastModifiedBy,
)

fun ScoreProfile.toEntity(): ScoreProfileEntity {
    val entity = ScoreProfileEntity(
        id = id,
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
        type = type?.let { ScoreProfileEntity.ScoreProfileType.valueOf(it.name) },
    )

    entity.penalties = penalties?.map { penalty ->
        PenaltyEntity(
            id = penalty.id,
            scoreId = penalty.scoreId,
            title = penalty.title,
            type = penalty.type?.let { PenaltyEntity.PenaltyType.valueOf(it.name) },
            value = penalty.value,
        ).also { it.scoreProfile = entity }
    }?.toMutableList()

    entity.actions = actions?.map { action ->
        ScoreActionEntity(
            id = action.id,
            title = action.title,
            value = action.value,
        ).also { actionEntity ->
            actionEntity.scoreProfile = entity
            actionEntity.overrides = action.overrides.map { override ->
                ScoreOverrideEntity(
                    id = override.id,
                    region = override.region?.let { MatchScoreEntity.RegionType.valueOf(it.name) },
                    value = override.value,
                ).also { it.action = actionEntity }
            }.toMutableList()
        }
    }?.toMutableList()

    return entity
}
