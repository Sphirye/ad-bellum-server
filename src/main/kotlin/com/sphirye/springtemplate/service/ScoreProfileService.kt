package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.ScoreProfile
import com.sphirye.springtemplate.model.ScoreProfile.ScoreProfileType
import com.sphirye.springtemplate.repository.ScoreProfileRepository
import org.springframework.stereotype.Service

@Service
class ScoreProfileService(
    private val _ScoreProfileRepository: ScoreProfileRepository,
): BaseService<ScoreProfile, Long>(_ScoreProfileRepository) {

    fun instance(scoreProfile: ScoreProfile): ScoreProfile {
        scoreProfile.type = ScoreProfileType.INSTANCE
        return create(scoreProfile)
    }

}