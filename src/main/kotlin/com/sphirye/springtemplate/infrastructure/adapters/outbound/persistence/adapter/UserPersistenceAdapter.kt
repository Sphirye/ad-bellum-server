package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.adapter

import com.sphirye.springtemplate.domain.model.User
import com.sphirye.springtemplate.domain.port.outbound.UserPersistencePort
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toDomain
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository.UserRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
) : UserPersistencePort {

    override fun findById(id: Long): User? =
        userRepository.findById(id).map { it.toDomain() }.orElse(null)

    override fun findByEmail(email: String): User? =
        userRepository.findByEmail(email)?.toDomain()

    override fun findAll(pageable: Pageable): Page<User> =
        userRepository.findAll(pageable).map { it.toDomain() }

    override fun findAllByExample(example: User, pageable: Pageable): Page<User> {
        val matcher = ExampleMatcher
            .matchingAny()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        return userRepository.findAll(Example.of(example.toEntity(), matcher), pageable).map { it.toDomain() }
    }

    override fun existsById(id: Long): Boolean = userRepository.existsById(id)

    override fun existsByEmail(email: String): Boolean = userRepository.existsByEmail(email)

    override fun count(): Long = userRepository.count()

    @Transactional
    override fun save(user: User): User =
        userRepository.save(user.toEntity()).toDomain()

    @Transactional
    override fun deleteById(id: Long) = userRepository.deleteById(id)
}
