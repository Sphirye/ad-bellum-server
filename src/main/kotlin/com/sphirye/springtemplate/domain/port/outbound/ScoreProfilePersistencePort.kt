package com.sphirye.springtemplate.domain.port.outbound

import com.sphirye.springtemplate.domain.model.ScoreProfile
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ScoreProfilePersistencePort {
    fun findById(id: Long): ScoreProfile?
    fun findAll(pageable: Pageable): Page<ScoreProfile>
    fun findAllByExample(example: ScoreProfile, pageable: Pageable): Page<ScoreProfile>
    fun save(profile: ScoreProfile): ScoreProfile
    fun deleteById(id: Long)
    fun existsById(id: Long): Boolean
}
