package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.adapter

import com.sphirye.springtemplate.domain.model.Match
import com.sphirye.springtemplate.domain.model.MatchFilter
import com.sphirye.springtemplate.domain.port.outbound.MatchPersistencePort
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toDomain
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toSpecification
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository.MatchRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class MatchPersistenceAdapter(
    private val matchRepository: MatchRepository,
) : MatchPersistencePort {

    override fun findById(id: Long): Match? =
        matchRepository.findById(id).map { it.toDomain() }.orElse(null)

    override fun findAll(pageable: Pageable): Page<Match> =
        matchRepository.findAll(pageable).map { it.toDomain() }

    override fun findAllByFilter(filter: MatchFilter, pageable: Pageable): Page<Match> =
        matchRepository.findAll(filter.toSpecification(), pageable).map { it.toDomain() }

    override fun existsById(id: Long): Boolean = matchRepository.existsById(id)

    override fun existsByIdAndState(id: Long, state: Match.MatchState): Boolean =
        matchRepository.existsByIdAndState(id, state.let { com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Match.MatchState.valueOf(it.name) })

    @Transactional
    override fun save(match: Match): Match =
        matchRepository.save(match.toEntity()).toDomain()

    @Transactional
    override fun deleteById(id: Long) = matchRepository.deleteById(id)
}
