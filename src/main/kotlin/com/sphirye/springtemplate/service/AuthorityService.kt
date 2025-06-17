package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Authority
import com.sphirye.springtemplate.repository.AuthorityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorityService(
    private var _authorityRepository: AuthorityRepository,
): BaseService<Authority, Long>(_authorityRepository) {

    fun findByRole(role: Authority.Role): Authority {
        return _authorityRepository.findByRole(role)
    }

    fun existsByRole(role: Authority.Role): Boolean {
        return _authorityRepository.existsByRole(role)
    }

}