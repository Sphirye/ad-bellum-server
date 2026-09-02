package com.sphirye.springtemplate.application.service

import com.sphirye.shared.exception.exceptions.ResourceNotFoundException
import com.sphirye.springtemplate.domain.model.PenaltyRecords
import com.sphirye.springtemplate.domain.port.inbound.PenaltyRecordsUseCase
import com.sphirye.springtemplate.domain.port.outbound.PenaltyRecordsPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PenaltyRecordsService(
    private val penaltyRecordsPersistencePort: PenaltyRecordsPersistencePort,
) : PenaltyRecordsUseCase {

    @Transactional
    override fun create(record: PenaltyRecords): PenaltyRecords =
        penaltyRecordsPersistencePort.save(record)
}
