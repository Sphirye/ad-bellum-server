package com.sphirye.springtemplate.application.service

import com.sphirye.shared.exception.exceptions.ResourceNotFoundException
import com.sphirye.springtemplate.domain.model.User
import com.sphirye.springtemplate.domain.port.inbound.UserUseCase
import com.sphirye.springtemplate.domain.port.outbound.PasswordEncoderPort
import com.sphirye.springtemplate.domain.port.outbound.UserPersistencePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userPersistencePort: UserPersistencePort,
    private val passwordEncoderPort: PasswordEncoderPort,
) : UserUseCase {

    override fun create(user: User): User {
        val encodedUser = user.copy(
            id = null,
            password = passwordEncoderPort.encode(user.password.orEmpty()),
        )
        return userPersistencePort.save(encodedUser)
    }

    override fun findByEmail(email: String): User {
        if (!existsByEmail(email)) {
            throw ResourceNotFoundException("Email '$email' not found")
        }
        return userPersistencePort.findByEmail(email) ?: throw ResourceNotFoundException("Email '$email' not found")
    }

    override fun existsByEmail(email: String): Boolean {
        return userPersistencePort.existsByEmail(email)
    }

    override fun findById(id: Long): User =
        userPersistencePort.findById(id) ?: throw ResourceNotFoundException("Resource with id $id does not exists")

    override fun findAll(example: User?, pageable: Pageable): Page<User> {
        return if (example != null) {
            userPersistencePort.findAllByExample(example, pageable)
        } else {
            userPersistencePort.findAll(pageable)
        }
    }

    override fun delete(id: Long) {
        _validateResourceExistsById(id)
        userPersistencePort.deleteById(id)
    }

    private fun _validateResourceExistsById(id: Long) {
        if (!userPersistencePort.existsById(id)) {
            throw ResourceNotFoundException("Resource with id $id does not exists")
        }
    }
}
