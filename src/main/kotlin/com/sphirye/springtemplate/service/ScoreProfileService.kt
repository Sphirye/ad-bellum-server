package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.ScoreProfile
import com.sphirye.springtemplate.model.ScoreProfile.ScoreProfileType
import com.sphirye.springtemplate.repository.ScoreProfileRepository
import org.springframework.stereotype.Service

@Service
class ScoreProfileService(
    private val _scoreProfileRepository: ScoreProfileRepository,
): BaseService<ScoreProfile, Long>(_scoreProfileRepository) {

    fun instance(scoreProfile: ScoreProfile): ScoreProfile {
        scoreProfile.type = ScoreProfileType.INSTANCE
        scoreProfile.id = null
        return create(scoreProfile)
    }

}