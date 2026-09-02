package com.sphirye.springtemplate.domain.port.inbound

import com.sphirye.springtemplate.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserUseCase {
    fun findById(id: Long): User
    fun findByEmail(email: String): User
    fun findAll(example: User?, pageable: Pageable): Page<User>
    fun existsByEmail(email: String): Boolean
    fun create(user: User): User
    fun delete(id: Long)
}
