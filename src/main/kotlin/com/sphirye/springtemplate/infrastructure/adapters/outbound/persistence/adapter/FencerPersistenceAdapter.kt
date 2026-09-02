package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.adapter

import com.sphirye.springtemplate.application.dto.FencerStats
import com.sphirye.springtemplate.domain.model.Fencer
import com.sphirye.springtemplate.domain.port.outbound.FencerPersistencePort
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toDomain
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository.FencerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class FencerPersistenceAdapter(
    private val fencerRepository: FencerRepository,
) : FencerPersistencePort {

    override fun findById(id: Long): Fencer? =
        fencerRepository.findById(id).map { it.toDomain() }.orElse(null)

    override fun findAll(pageable: Pageable): Page<Fencer> =
        fencerRepository.findAll(pageable).map { it.toDomain() }

    override fun findByNameContaining(name: String, pageable: Pageable): Page<Fencer> =
        fencerRepository.findAllByNameIgnoreCaseContaining(name, pageable).map { it.toDomain() }

    override fun getStats(fencerId: Long): FencerStats {
        val stats = fencerRepository.getFencerStats(fencerId)
        return FencerStats(
            totalPoints = stats.getTotalPoints(),
            totalThrusts = stats.getTotalThrusts(),
            totalCuts = stats.getTotalCuts(),
            totalSlices = stats.getTotalSlices(),
            totalDoubles = stats.getTotalDoubles(),
            totalControls = stats.getTotalControls(),
            totalAfterblows = stats.getTotalAfterblows(),
            fromDate = stats.getFromDate(),
        )
    }

    @Transactional
    override fun save(fencer: Fencer): Fencer =
        fencerRepository.save(fencer.toEntity()).toDomain()

    @Transactional
    override fun deleteById(id: Long) = fencerRepository.deleteById(id)

    override fun existsById(id: Long): Boolean = fencerRepository.existsById(id)
}
