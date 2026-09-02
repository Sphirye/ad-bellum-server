package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.adapter

import com.sphirye.springtemplate.domain.model.Penalty
import com.sphirye.springtemplate.domain.port.outbound.PenaltyPersistencePort
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toDomain
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository.PenaltyRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class PenaltyPersistenceAdapter(
    private val penaltyRepository: PenaltyRepository,
) : PenaltyPersistencePort {

    override fun findAll(pageable: Pageable): Page<Penalty> =
        penaltyRepository.findAll(pageable).map { it.toDomain() }

    override fun findAllByExample(example: Penalty, pageable: Pageable): Page<Penalty> {
        val matcher = ExampleMatcher
            .matchingAny()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        return penaltyRepository.findAll(Example.of(example.toEntity(), matcher), pageable).map { it.toDomain() }
    }

    @Transactional
    override fun save(penalty: Penalty): Penalty =
        penaltyRepository.save(penalty.toEntity()).toDomain()

    @Transactional
    override fun deleteById(id: Long) = penaltyRepository.deleteById(id)

    override fun existsById(id: Long): Boolean = penaltyRepository.existsById(id)
}
