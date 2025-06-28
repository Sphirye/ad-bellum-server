package com.sphirye.springtemplate.service

import com.sphirye.shared.exception.exceptions.ConflictException
import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Penalty
import com.sphirye.springtemplate.model.Penalty.PenaltyType
import com.sphirye.springtemplate.repository.PenaltyRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PenaltyService(
    private val _penaltyRepository: PenaltyRepository,
): BaseService<Penalty, Long>(_penaltyRepository) {

    override fun beforeCreate(entity: Penalty): Penalty {
        if (entity.type == PenaltyType.INSTANCE) {
            entity.id = null
            entity.scoreProfileId = null

            if (entity.fencerId == null) {
                throw ConflictException("Instanced penalties must reference a fencer.")
            }
        }

        if (entity.type  == PenaltyType.TEMPLATE) {
            entity.scoreId = null
            entity.fencerId = null

            if (entity.scoreProfileId == null) {
                throw ConflictException("Template penalties must reference a score profile.")
            }
        }

        return entity
    }

    fun instance(entity: Penalty): Penalty {
        entity.type = Penalty.PenaltyType.INSTANCE
        entity.id = null
        entity.scoreProfileId = null

        return create(entity)
    }

}