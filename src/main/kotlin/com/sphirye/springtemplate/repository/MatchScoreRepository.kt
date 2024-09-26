package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.MatchScore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchScoreRepository: JpaRepository<MatchScore, Long> {
}