package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.User
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.User as UserEntity

fun UserEntity.toDomain(): User = User(
    id = id,
    username = username,
    email = email,
    password = password,
    authorities = authorities.map { it.toDomain() }.toMutableSet(),
    createdDate = createdDate,
    lastModifiedDate = lastModifiedDate,
    createdById = createdById,
    lastModifiedBy = lastModifiedBy,
)

fun User.toEntity(): UserEntity = UserEntity(
    id = id,
    username = username,
    email = email,
    password = password,
    authorities = authorities.map { it.toEntity() }.toMutableSet(),
)
