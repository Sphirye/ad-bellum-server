package com.sphirye.springtemplate.domain.port.inbound

import com.sphirye.springtemplate.application.dto.FencerStats
import com.sphirye.springtemplate.domain.model.Fencer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FencerUseCase {
    fun findById(id: Long): Fencer
    fun searchByName(name: String?, pageable: Pageable): Page<Fencer>
    fun getStats(fencerId: Long): FencerStats
    fun create(fencer: Fencer): Fencer
    fun update(id: Long, fencer: Fencer): Fencer
    fun delete(id: Long)
}
