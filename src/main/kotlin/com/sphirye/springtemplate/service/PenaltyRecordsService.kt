package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.PenaltyRecords
import com.sphirye.springtemplate.repository.PenaltyRecordsRepository
import org.springframework.stereotype.Service

@Service
class PenaltyRecordsService(
    private val _penaltyRecordsRepository: PenaltyRecordsRepository,
): BaseService<PenaltyRecords, Long>(_penaltyRecordsRepository) {
}