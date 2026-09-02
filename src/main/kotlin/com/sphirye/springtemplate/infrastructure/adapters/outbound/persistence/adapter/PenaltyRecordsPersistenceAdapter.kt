package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.adapter

import com.sphirye.springtemplate.domain.model.PenaltyRecords
import com.sphirye.springtemplate.domain.port.outbound.PenaltyRecordsPersistencePort
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toDomain
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper.toEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.repository.PenaltyRecordsRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class PenaltyRecordsPersistenceAdapter(
    private val penaltyRecordsRepository: PenaltyRecordsRepository,
) : PenaltyRecordsPersistencePort {

    override fun save(record: PenaltyRecords): PenaltyRecords =
        penaltyRecordsRepository.save(record.toEntity()).toDomain()
}
