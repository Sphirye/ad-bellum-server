package com.sphirye.springtemplate.domain.port.outbound

import com.sphirye.springtemplate.domain.model.PenaltyRecords

interface PenaltyRecordsPersistencePort {
    fun save(record: PenaltyRecords): PenaltyRecords
}
