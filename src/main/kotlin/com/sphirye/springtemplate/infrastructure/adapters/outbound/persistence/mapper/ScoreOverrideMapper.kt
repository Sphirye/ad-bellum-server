package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.MatchScore
import com.sphirye.springtemplate.domain.model.ScoreOverride
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.MatchScore as MatchScoreEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.ScoreOverride as ScoreOverrideEntity

fun ScoreOverrideEntity.toDomain(): ScoreOverride = ScoreOverride(
    id = id,
    region = region?.let { MatchScore.RegionType.valueOf(it.name) },
    value = value,
    createdDate = createdDate,
    lastModifiedDate = lastModifiedDate,
    createdById = createdById,
    lastModifiedBy = lastModifiedBy,
)

fun ScoreOverride.toEntity(): ScoreOverrideEntity = ScoreOverrideEntity(
    id = id,
    region = region?.let { MatchScoreEntity.RegionType.valueOf(it.name) },
    value = value,
)
