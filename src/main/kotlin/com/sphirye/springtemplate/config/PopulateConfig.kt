package com.sphirye.springtemplate.config

import com.sphirye.springtemplate.model.Authority
import com.sphirye.springtemplate.model.User
import com.sphirye.springtemplate.repository.AuthorityRepository
import com.sphirye.springtemplate.repository.UserRepository
import com.sphirye.springtemplate.service.UserService
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PopulateConfig {


    @Autowired
    private lateinit var _authorityRepository: AuthorityRepository

    @Autowired
    private lateinit var _userRepository: UserRepository

    @Autowired
    private lateinit var _userService: UserService

    @PostConstruct
    fun init() {
        Authority.Role.values().forEach {
            if (!_authorityRepository.existsByRole(it)) {
                _authorityRepository.save(Authority(role = it))
            }
        }

        if (_userRepository.count() <= 0) {
            val user = User(
                username = "sphirye",
                password = "1234",
                email = "sphirye@gmail.com",
            )

            _userService.create(user)
        }
    }

}