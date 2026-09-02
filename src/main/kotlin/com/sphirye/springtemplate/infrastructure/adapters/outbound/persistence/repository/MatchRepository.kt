package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository

import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Match
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface MatchRepository : JpaRepository<Match, Long>, JpaSpecificationExecutor<Match> {

    fun existsByIdAndState(id: Long, state: Match.MatchState): Boolean

}
