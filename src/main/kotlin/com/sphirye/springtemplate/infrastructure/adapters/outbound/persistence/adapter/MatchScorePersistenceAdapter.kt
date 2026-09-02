package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.adapter

import com.sphirye.springtemplate.domain.model.MatchScore
import com.sphirye.springtemplate.domain.port.outbound.MatchScorePersistencePort
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toDomain
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository.MatchScoreRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class MatchScorePersistenceAdapter(
    private val matchScoreRepository: MatchScoreRepository,
) : MatchScorePersistencePort {

    override fun findAll(pageable: Pageable): Page<MatchScore> =
        matchScoreRepository.findAll(pageable).map { it.toDomain() }

    override fun findAllByExample(example: MatchScore, pageable: Pageable): Page<MatchScore> {
        val matcher = ExampleMatcher
            .matchingAny()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        return matchScoreRepository.findAll(Example.of(example.toEntity(), matcher), pageable).map { it.toDomain() }
    }

    @Transactional
    override fun save(score: MatchScore): MatchScore =
        matchScoreRepository.save(score.toEntity()).toDomain()

    @Transactional
    override fun deleteById(id: Long) = matchScoreRepository.deleteById(id)

    override fun existsById(id: Long): Boolean = matchScoreRepository.existsById(id)
}
