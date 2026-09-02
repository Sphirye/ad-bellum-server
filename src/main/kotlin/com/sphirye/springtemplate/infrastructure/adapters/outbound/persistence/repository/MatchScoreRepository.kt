package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository

import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.MatchScore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchScoreRepository: JpaRepository<MatchScore, Long> {
}
