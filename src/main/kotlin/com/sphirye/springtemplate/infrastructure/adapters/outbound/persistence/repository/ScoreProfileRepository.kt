package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository

import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.ScoreProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScoreProfileRepository : JpaRepository<ScoreProfile, Long> {
}
