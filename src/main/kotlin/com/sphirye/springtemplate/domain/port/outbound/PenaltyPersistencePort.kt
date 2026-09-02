package com.sphirye.springtemplate.domain.port.outbound

import com.sphirye.springtemplate.domain.model.Penalty
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PenaltyPersistencePort {
    fun findAll(pageable: Pageable): Page<Penalty>
    fun findAllByExample(example: Penalty, pageable: Pageable): Page<Penalty>
    fun save(penalty: Penalty): Penalty
    fun deleteById(id: Long)
    fun existsById(id: Long): Boolean
}
