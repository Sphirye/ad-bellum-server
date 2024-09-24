package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.MatchPoint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchPointRepository: JpaRepository<MatchPoint, Long> {
}