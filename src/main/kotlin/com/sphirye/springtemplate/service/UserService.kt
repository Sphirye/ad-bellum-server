package com.sphirye.springtemplate.service

import com.sphirye.shared.exception.exceptions.ResourceNotFoundException
import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.User
import com.sphirye.springtemplate.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val _userRepository: UserRepository,
): BaseService<User, Long>(_userRepository) {

    @Autowired @Lazy
    private lateinit var _passwordEncoder: PasswordEncoder

    override fun beforeCreate(entity: User): User {
        entity.id = null
        entity.password = _passwordEncoder.encode(entity.password)
        
        return super.beforeCreate(entity)
    }

    fun findByEmail(email: String): User {
        if (!existsByEmail(email)) {
            throw ResourceNotFoundException("Email '$email' not found")
        }
        return _userRepository.findByEmail(email)
    }

    fun existsByEmail(email: String): Boolean {
        return _userRepository.existsByEmail(email)
    }

}