package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.Penalty
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Penalty as PenaltyEntity

fun PenaltyEntity.toDomain(): Penalty = Penalty(
    id = id,
    scoreId = scoreId,
    title = title,
    type = type?.let { Penalty.PenaltyType.valueOf(it.name) },
    value = value,
    createdDate = createdDate,
    lastModifiedDate = lastModifiedDate,
    createdById = createdById,
    lastModifiedBy = lastModifiedBy,
)

fun Penalty.toEntity(): PenaltyEntity = PenaltyEntity(
    id = id,
    scoreId = scoreId,
    title = title,
    type = type?.let { PenaltyEntity.PenaltyType.valueOf(it.name) },
    value = value,
)
