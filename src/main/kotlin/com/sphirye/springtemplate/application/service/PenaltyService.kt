package com.sphirye.springtemplate.application.service

import com.sphirye.shared.exception.exceptions.ResourceNotFoundException
import com.sphirye.springtemplate.domain.model.Penalty
import com.sphirye.springtemplate.domain.port.inbound.PenaltyUseCase
import com.sphirye.springtemplate.domain.port.outbound.PenaltyPersistencePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PenaltyService(
    private val penaltyPersistencePort: PenaltyPersistencePort,
) : PenaltyUseCase {

    override fun findAll(example: Penalty?, pageable: Pageable): Page<Penalty> {
        return if (example != null) {
            penaltyPersistencePort.findAllByExample(example, pageable)
        } else {
            penaltyPersistencePort.findAll(pageable)
        }
    }

    override fun create(penalty: Penalty): Penalty =
        penaltyPersistencePort.save(penalty)

    override fun update(id: Long, penalty: Penalty): Penalty {
        _validateResourceExistsById(id)
        return penaltyPersistencePort.save(penalty.copy(id = id))
    }

    override fun delete(id: Long) {
        _validateResourceExistsById(id)
        penaltyPersistencePort.deleteById(id)
    }

    private fun _validateResourceExistsById(id: Long) {
        if (!penaltyPersistencePort.existsById(id)) {
            throw ResourceNotFoundException("Resource with id $id does not exists")
        }
    }
}
