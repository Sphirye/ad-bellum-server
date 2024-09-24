package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Fencer
import com.sphirye.springtemplate.repository.FencerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FencerService(
    private val _fencerRepository: FencerRepository
): BaseService<Fencer, Long>(_fencerRepository) {

    fun searchByName(name: String?, pageRequest: PageRequest): Page<Fencer> {
        return if (name.isNullOrEmpty()) {
            _fencerRepository.findAll(pageRequest)
        } else {
            _fencerRepository.findAllByNameIgnoreCaseContaining(name, pageRequest)
        }
    }

}