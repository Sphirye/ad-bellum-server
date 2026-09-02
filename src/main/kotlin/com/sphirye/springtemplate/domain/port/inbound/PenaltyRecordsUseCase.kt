package com.sphirye.springtemplate.domain.port.inbound

import com.sphirye.springtemplate.domain.model.PenaltyRecords

interface PenaltyRecordsUseCase {
    fun create(record: PenaltyRecords): PenaltyRecords
}
