package com.sphirye.springtemplate.domain.port.outbound

import com.sphirye.springtemplate.application.dto.FencerStats
import com.sphirye.springtemplate.domain.model.Fencer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FencerPersistencePort {
    fun findById(id: Long): Fencer?
    fun findAll(pageable: Pageable): Page<Fencer>
    fun findByNameContaining(name: String, pageable: Pageable): Page<Fencer>
    fun getStats(fencerId: Long): FencerStats
    fun save(fencer: Fencer): Fencer
    fun deleteById(id: Long)
    fun existsById(id: Long): Boolean
}
