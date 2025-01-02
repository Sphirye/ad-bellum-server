package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.ScoreProfile
import com.sphirye.springtemplate.repository.ScoreProfileRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service

@Service
class ScoreProfileService(
    private val _ScoreProfileRepository: ScoreProfileRepository,
    private val entityManager: EntityManager
): BaseService<ScoreProfile, Long>(_ScoreProfileRepository) {

    fun instanceScoreProfile(id: Long): ScoreProfile {
        val template = findById(id)

        template.id = null
        template.createdDate = null
        template.createdBy = null

        entityManager.detach(template)
        return create(template)
    }

}