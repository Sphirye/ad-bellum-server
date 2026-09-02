package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.PenaltyRecords
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.PenaltyRecords as PenaltyRecordsEntity

fun PenaltyRecordsEntity.toDomain(): PenaltyRecords = PenaltyRecords(
    id = id,
    scoreId = scoreId,
    penaltyId = penaltyId,
    fencerId = fencerId,
    penalty = penalty?.toDomain(),
    fencer = fencer?.toDomain(),
)

fun PenaltyRecords.toEntity(): PenaltyRecordsEntity = PenaltyRecordsEntity(
    id = id,
    scoreId = scoreId,
    penaltyId = penaltyId,
    fencerId = fencerId,
)
