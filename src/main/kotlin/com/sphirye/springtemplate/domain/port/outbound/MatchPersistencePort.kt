package com.sphirye.springtemplate.domain.port.outbound

import com.sphirye.springtemplate.domain.model.Match
import com.sphirye.springtemplate.domain.model.MatchFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MatchPersistencePort {
    fun findById(id: Long): Match?
    fun findAll(pageable: Pageable): Page<Match>
    fun findAllByFilter(filter: MatchFilter, pageable: Pageable): Page<Match>
    fun existsById(id: Long): Boolean
    fun existsByIdAndState(id: Long, state: Match.MatchState): Boolean
    fun save(match: Match): Match
    fun deleteById(id: Long)
}
