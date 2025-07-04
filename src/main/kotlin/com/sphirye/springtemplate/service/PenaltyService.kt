package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Penalty
import com.sphirye.springtemplate.model.Penalty.PenaltyType
import com.sphirye.springtemplate.model.ScoreProfile
import com.sphirye.springtemplate.model.ScoreProfile.ScoreProfileType
import com.sphirye.springtemplate.repository.PenaltyRepository
import org.springframework.stereotype.Service

@Service
class PenaltyService(
    private val _penaltyRepository: PenaltyRepository,
): BaseService<Penalty, Long>(_penaltyRepository) {

    fun createFromScoreProfile(scoreProfile: ScoreProfile) {
        scoreProfile.penalties?.forEach { penalty ->

            penalty.id = null
            penalty.scoreProfileId = scoreProfile.id

            if (scoreProfile.type == ScoreProfileType.INSTANCE) {
                penalty.type = PenaltyType.INSTANCE
            }

            if (scoreProfile.type == ScoreProfileType.TEMPLATE) {
                penalty.type = PenaltyType.TEMPLATE
            }

            create(penalty)
        }
    }

}