package com.sphirye.springtemplate.domain.port.inbound

import com.sphirye.springtemplate.domain.model.ScoreProfile
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ScoreProfileUseCase {
    fun findById(id: Long): ScoreProfile
    fun findAll(example: ScoreProfile?, pageable: Pageable): Page<ScoreProfile>
    fun createProfileScore(profile: ScoreProfile): ScoreProfile
    fun instance(profile: ScoreProfile): ScoreProfile
    fun update(id: Long, profile: ScoreProfile): ScoreProfile
    fun updateTimeLeft(id: Long, timeLeft: Int): ScoreProfile
    fun updateElapsedTime(id: Long, elapsedTime: Int): ScoreProfile
    fun delete(id: Long)
}
