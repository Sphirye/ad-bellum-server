package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.Group
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Group as GroupEntity

fun GroupEntity.toDomain(): Group = Group(
    id = id,
    title = title,
    createdDate = createdDate,
    lastModifiedDate = lastModifiedDate,
    createdById = createdById,
    lastModifiedBy = lastModifiedBy,
)

fun Group.toEntity(): GroupEntity = GroupEntity(
    id = id,
    title = title,
)
