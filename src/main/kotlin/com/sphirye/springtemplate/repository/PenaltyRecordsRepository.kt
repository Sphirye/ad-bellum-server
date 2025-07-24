package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.PenaltyRecords
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PenaltyRecordsRepository: JpaRepository<PenaltyRecords, Long> {
}