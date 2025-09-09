package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.ScoreAction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScoreActionRepository: JpaRepository<ScoreAction, Long> {
}