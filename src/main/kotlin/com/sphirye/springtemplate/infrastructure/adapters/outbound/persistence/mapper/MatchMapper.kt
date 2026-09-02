package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.Match
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Match as MatchEntity

fun MatchEntity.toDomain(): Match = Match(
    id = id,
    fencer_1_id = fencer_1_id,
    fencer_1 = fencer_1?.toDomain(),
    fencer_2_id = fencer_2_id,
    fencer_2 = fencer_2?.toDomain(),
    state = state?.let { Match.MatchState.valueOf(it.name) },
    resolution = resolution?.let { Match.MatchResolution.valueOf(it.name) },
    scoreProfileId = scoreProfileId,
    groupId = groupId,
    winnerFencerId = winnerFencerId,
    group = group?.toDomain(),
    scoreProfile = scoreProfile?.toDomain(),
    scores = scores?.map { it.toDomain() },
    createdDate = createdDate,
    lastModifiedDate = lastModifiedDate,
    createdById = createdById,
    lastModifiedBy = lastModifiedBy,
)

fun Match.toEntity(): MatchEntity = MatchEntity(
    id = id,
    fencer_1_id = fencer_1_id,
    fencer_2_id = fencer_2_id,
    state = state?.let { MatchEntity.MatchState.valueOf(it.name) },
    resolution = resolution?.let { MatchEntity.MatchResolution.valueOf(it.name) },
    scoreProfileId = scoreProfileId,
    groupId = groupId,
    winnerFencerId = winnerFencerId,
)
