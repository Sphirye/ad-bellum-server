package com.sphirye.springtemplate.domain.port.inbound

import com.sphirye.springtemplate.domain.model.Match
import com.sphirye.springtemplate.domain.model.MatchFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MatchUseCase {
    fun findById(id: Long): Match
    fun findAll(filter: MatchFilter?, pageable: Pageable): Page<Match>
    fun create(match: Match): Match
    fun update(id: Long, match: Match): Match
    fun finish(id: Long, match: Match): Match
    fun delete(id: Long)
    fun hasMatchFinished(id: Long): Boolean
}
