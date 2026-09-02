package com.sphirye.springtemplate.domain.port.outbound

import com.sphirye.springtemplate.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserPersistencePort {
    fun findById(id: Long): User?
    fun findByEmail(email: String): User?
    fun findAll(pageable: Pageable): Page<User>
    fun findAllByExample(example: User, pageable: Pageable): Page<User>
    fun existsById(id: Long): Boolean
    fun existsByEmail(email: String): Boolean
    fun count(): Long
    fun save(user: User): User
    fun deleteById(id: Long)
}
