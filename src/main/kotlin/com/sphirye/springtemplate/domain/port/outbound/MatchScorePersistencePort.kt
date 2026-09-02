package com.sphirye.springtemplate.domain.port.outbound

import com.sphirye.springtemplate.domain.model.MatchScore
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MatchScorePersistencePort {
    fun findAll(pageable: Pageable): Page<MatchScore>
    fun findAllByExample(example: MatchScore, pageable: Pageable): Page<MatchScore>
    fun save(score: MatchScore): MatchScore
    fun deleteById(id: Long)
    fun existsById(id: Long): Boolean
}
