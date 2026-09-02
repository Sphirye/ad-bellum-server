package com.sphirye.springtemplate.domain.port.inbound

import com.sphirye.springtemplate.domain.model.MatchScore
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MatchScoreUseCase {
    fun findAll(example: MatchScore?, pageable: Pageable): Page<MatchScore>
    fun createScore(score: MatchScore): MatchScore
    fun update(id: Long, score: MatchScore): MatchScore
    fun delete(id: Long)
}
