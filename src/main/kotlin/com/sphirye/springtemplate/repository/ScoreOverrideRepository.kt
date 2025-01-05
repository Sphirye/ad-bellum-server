package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.ScoreOverride
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScoreOverrideRepository : JpaRepository<ScoreOverride, Long> {
}