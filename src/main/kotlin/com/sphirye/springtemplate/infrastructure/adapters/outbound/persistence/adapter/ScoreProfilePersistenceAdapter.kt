package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.adapter

import com.sphirye.springtemplate.domain.model.ScoreProfile
import com.sphirye.springtemplate.domain.port.outbound.ScoreProfilePersistencePort
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toDomain
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository.ScoreProfileRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class ScoreProfilePersistenceAdapter(
    private val scoreProfileRepository: ScoreProfileRepository,
) : ScoreProfilePersistencePort {

    override fun findById(id: Long): ScoreProfile? =
        scoreProfileRepository.findById(id).map { it.toDomain() }.orElse(null)

    override fun findAll(pageable: Pageable): Page<ScoreProfile> =
        scoreProfileRepository.findAll(pageable).map { it.toDomain() }

    override fun findAllByExample(example: ScoreProfile, pageable: Pageable): Page<ScoreProfile> {
        val matcher = ExampleMatcher
            .matchingAny()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        return scoreProfileRepository.findAll(Example.of(example.toEntity(), matcher), pageable).map { it.toDomain() }
    }

    @Transactional
    override fun save(profile: ScoreProfile): ScoreProfile =
        scoreProfileRepository.save(profile.toEntity()).toDomain()

    @Transactional
    override fun deleteById(id: Long) = scoreProfileRepository.deleteById(id)

    override fun existsById(id: Long): Boolean = scoreProfileRepository.existsById(id)
}
