package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.Penalty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PenaltyRepository: JpaRepository<Penalty, Long> {
}