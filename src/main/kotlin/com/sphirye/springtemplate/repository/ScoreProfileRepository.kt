package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.ScoreProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScoreProfileRepository : JpaRepository<ScoreProfile, Long> {
}