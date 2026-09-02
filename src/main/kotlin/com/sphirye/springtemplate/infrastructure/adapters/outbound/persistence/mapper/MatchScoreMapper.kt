package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.MatchScore
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.MatchScore as MatchScoreEntity

fun MatchScoreEntity.toDomain(): MatchScore = MatchScore(
    id = id,
    scorerId = scorerId,
    actionId = actionId,
    verdict = verdict?.let { MatchScore.Verdict.valueOf(it.name) },
    matchId = matchId,
    afterblow = afterblow,
    control = control,
    region = region?.let { MatchScore.RegionType.valueOf(it.name) },
    scorer = scorer?.toDomain(),
    action = action?.toDomain(),
    penaltyRecords = penaltyRecords.map { it.toDomain() },
    createdDate = createdDate,
    lastModifiedDate = lastModifiedDate,
    createdById = createdById,
    lastModifiedBy = lastModifiedBy,
)

fun MatchScore.toEntity(): MatchScoreEntity = MatchScoreEntity(
    id = id,
    scorerId = scorerId,
    actionId = actionId,
    verdict = verdict?.let { MatchScoreEntity.Verdict.valueOf(it.name) },
    matchId = matchId,
    afterblow = afterblow,
    control = control,
    region = region?.let { MatchScoreEntity.RegionType.valueOf(it.name) },
)
