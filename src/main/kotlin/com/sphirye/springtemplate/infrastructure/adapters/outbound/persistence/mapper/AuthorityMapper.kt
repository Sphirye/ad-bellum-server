package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.Authority
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Authority as AuthorityEntity

fun AuthorityEntity.toDomain(): Authority = Authority(
    id = id,
    role = Authority.Role.valueOf(role.name),
    description = description,
    enabled = enabled,
)

fun Authority.toEntity(): AuthorityEntity = AuthorityEntity(
    id = id,
    role = AuthorityEntity.Role.valueOf(role.name),
    description = description,
    enabled = enabled,
)
