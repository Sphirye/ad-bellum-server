package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.adapter

import com.sphirye.springtemplate.domain.model.Group
import com.sphirye.springtemplate.domain.port.outbound.GroupPersistencePort
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toDomain
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository.GroupRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class GroupPersistenceAdapter(
    private val groupRepository: GroupRepository,
) : GroupPersistencePort {

    override fun findById(id: Long): Group? =
        groupRepository.findById(id).map { it.toDomain() }.orElse(null)

    override fun findAll(pageable: Pageable): Page<Group> =
        groupRepository.findAll(pageable).map { it.toDomain() }

    override fun findAllByExample(example: Group, pageable: Pageable): Page<Group> {
        val matcher = ExampleMatcher
            .matchingAny()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        return groupRepository.findAll(Example.of(example.toEntity(), matcher), pageable).map { it.toDomain() }
    }

    @Transactional
    override fun save(group: Group): Group =
        groupRepository.save(group.toEntity()).toDomain()

    @Transactional
    override fun deleteById(id: Long) = groupRepository.deleteById(id)

    override fun existsById(id: Long): Boolean = groupRepository.existsById(id)
}
