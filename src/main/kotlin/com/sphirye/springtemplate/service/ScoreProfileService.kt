package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.ProfileScore
import com.sphirye.springtemplate.repository.ProfileScoreRepository
import org.springframework.stereotype.Service

@Service
class ScoreProfileService(
    private val _profileScoreRepository: ProfileScoreRepository
): BaseService<ProfileScore, Long>(_profileScoreRepository) {

}