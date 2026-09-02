package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.Fencer
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Fencer as FencerEntity

fun FencerEntity.toDomain(): Fencer = Fencer(
    id = id,
    name = name,
    email = email,
    createdDate = createdDate,
    lastModifiedDate = lastModifiedDate,
    createdById = createdById,
    lastModifiedBy = lastModifiedBy,
)

fun Fencer.toEntity(): FencerEntity = FencerEntity(
    id = id,
    name = name,
    email = email,
)
