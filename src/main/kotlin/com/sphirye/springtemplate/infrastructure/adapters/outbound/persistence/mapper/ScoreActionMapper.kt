package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.ScoreAction
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.ScoreAction as ScoreActionEntity

fun ScoreActionEntity.toDomain(): ScoreAction = ScoreAction(
    id = id,
    title = title,
    value = value,
    overrides = overrides.map { it.toDomain() }.toMutableList(),
    createdDate = createdDate,
    lastModifiedDate = lastModifiedDate,
    createdById = createdById,
    lastModifiedBy = lastModifiedBy,
)

fun ScoreAction.toEntity(): ScoreActionEntity = ScoreActionEntity(
    id = id,
    title = title,
    value = value,
)
