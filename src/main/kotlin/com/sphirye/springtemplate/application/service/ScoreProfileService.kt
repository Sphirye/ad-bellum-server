package com.sphirye.springtemplate.application.service

import com.sphirye.shared.exception.exceptions.ResourceNotFoundException
import com.sphirye.springtemplate.domain.model.ScoreProfile
import com.sphirye.springtemplate.domain.model.ScoreProfile.ScoreProfileType
import com.sphirye.springtemplate.domain.port.inbound.ScoreProfileUseCase
import com.sphirye.springtemplate.domain.port.outbound.ScoreProfilePersistencePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ScoreProfileService(
    private val scoreProfilePersistencePort: ScoreProfilePersistencePort,
) : ScoreProfileUseCase {

    @Transactional
    override fun createProfileScore(profile: ScoreProfile): ScoreProfile =
        scoreProfilePersistencePort.save(profile)

    @Transactional
    override fun instance(profile: ScoreProfile): ScoreProfile {
        val cleanProfile = profile.withoutIds().copy(type = ScoreProfileType.INSTANCE)
        return scoreProfilePersistencePort.save(cleanProfile)
    }

    override fun updateTimeLeft(id: Long, timeLeft: Int): ScoreProfile {
        val scoreProfile = findById(id)
        return scoreProfilePersistencePort.save(scoreProfile.copy(timeLeft = timeLeft))
    }

    override fun updateElapsedTime(id: Long, elapsedTime: Int): ScoreProfile {
        val scoreProfile = findById(id)
        return scoreProfilePersistencePort.save(scoreProfile.copy(elapsedTimeInSeconds = elapsedTime))
    }

    override fun findById(id: Long): ScoreProfile =
        scoreProfilePersistencePort.findById(id) ?: throw ResourceNotFoundException("Resource with id $id does not exists")

    override fun findAll(example: ScoreProfile?, pageable: Pageable): Page<ScoreProfile> {
        return if (example != null) {
            scoreProfilePersistencePort.findAllByExample(example, pageable)
        } else {
            scoreProfilePersistencePort.findAll(pageable)
        }
    }

    override fun update(id: Long, profile: ScoreProfile): ScoreProfile {
        _validateResourceExistsById(id)
        return scoreProfilePersistencePort.save(profile.copy(id = id))
    }

    override fun delete(id: Long) {
        _validateResourceExistsById(id)
        scoreProfilePersistencePort.deleteById(id)
    }

    private fun _validateResourceExistsById(id: Long) {
        if (!scoreProfilePersistencePort.existsById(id)) {
            throw ResourceNotFoundException("Resource with id $id does not exists")
        }
    }
}
