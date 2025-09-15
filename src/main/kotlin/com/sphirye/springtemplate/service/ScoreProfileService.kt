package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Penalty
import com.sphirye.springtemplate.model.Penalty.PenaltyType
import com.sphirye.springtemplate.model.ScoreProfile
import com.sphirye.springtemplate.model.ScoreProfile.ScoreProfileType
import com.sphirye.springtemplate.repository.ScoreProfileRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ScoreProfileService(
    private val _scoreProfileRepository: ScoreProfileRepository,
    private val _penaltyService: PenaltyService,
    private val _scoreActionService: ScoreActionService,
    private val _scoreOverrideService: ScoreOverrideService,
): BaseService<ScoreProfile, Long>(_scoreProfileRepository) {

    @Transactional
    fun createProfileScore(entity: ScoreProfile): ScoreProfile {
        return create(entity)
    }

    override fun afterCreated(entity: ScoreProfile) {
        _penaltyService.createFromScoreProfile(entity)
        _scoreActionService.createFromScoreProfile(entity)
    }

    override fun beforeUpdate(id: Long, entity: ScoreProfile): ScoreProfile {
        entity.penalties?.forEach { penalty ->
            if (penalty.scoreProfileId == null) {
                penalty.scoreProfileId = id
            }
        }

        entity.actions?.forEach { action ->
            if (action.scoreProfileId == null) {
                action.scoreProfileId = id
            }
        }

        entity.overrides.forEach { override ->
            //Check if override has an id and the referenced actionId exists in the profile.
            if (override.id != null && !entity.actions!!.any{ it.id == override.id }) {
                println("DELETING OVERRIDE")
                _scoreOverrideService.deleteById(override.id!!)
            }
        }

        return entity
    }

    fun instance(scoreProfile: ScoreProfile): ScoreProfile {
        scoreProfile.type = ScoreProfileType.INSTANCE
        scoreProfile.id = null
        return create(scoreProfile)
    }

    fun updateTimeLeft(id: Long, timeLeft: Int): ScoreProfile {
        val scoreProfile = findById(id)
        scoreProfile.timeLeft = timeLeft
        return _scoreProfileRepository.save(scoreProfile)
    }


}