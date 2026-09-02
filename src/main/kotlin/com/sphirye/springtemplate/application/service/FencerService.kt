package com.sphirye.springtemplate.application.service

import com.sphirye.shared.exception.exceptions.ResourceNotFoundException
import com.sphirye.springtemplate.application.dto.FencerStats
import com.sphirye.springtemplate.domain.model.Fencer
import com.sphirye.springtemplate.domain.port.inbound.FencerUseCase
import com.sphirye.springtemplate.domain.port.outbound.FencerPersistencePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FencerService(
    private val fencerPersistencePort: FencerPersistencePort,
) : FencerUseCase {

    override fun findById(id: Long): Fencer =
        fencerPersistencePort.findById(id) ?: throw ResourceNotFoundException("Resource with id $id does not exists")

    override fun searchByName(name: String?, pageable: Pageable): Page<Fencer> {
        return if (name.isNullOrEmpty()) {
            fencerPersistencePort.findAll(pageable)
        } else {
            fencerPersistencePort.findByNameContaining(name, pageable)
        }
    }

    override fun getStats(fencerId: Long): FencerStats =
        fencerPersistencePort.getStats(fencerId)

    override fun create(fencer: Fencer): Fencer =
        fencerPersistencePort.save(fencer)

    override fun update(id: Long, fencer: Fencer): Fencer {
        _validateResourceExistsById(id)
        return fencerPersistencePort.save(fencer.copy(id = id))
    }

    override fun delete(id: Long) {
        _validateResourceExistsById(id)
        fencerPersistencePort.deleteById(id)
    }

    private fun _validateResourceExistsById(id: Long) {
        if (!fencerPersistencePort.existsById(id)) {
            throw ResourceNotFoundException("Resource with id $id does not exists")
        }
    }
}
