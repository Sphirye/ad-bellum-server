package com.sphirye.springtemplate.domain.port.inbound

import com.sphirye.springtemplate.domain.model.Penalty
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PenaltyUseCase {
    fun findAll(example: Penalty?, pageable: Pageable): Page<Penalty>
    fun create(penalty: Penalty): Penalty
    fun update(id: Long, penalty: Penalty): Penalty
    fun delete(id: Long)
}
