package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Penalty
import com.sphirye.springtemplate.repository.PenaltyRepository
import org.springframework.stereotype.Service

@Service
class PenaltyService(
    private val _penaltyRepository: PenaltyRepository,
): BaseService<Penalty, Long>(_penaltyRepository) {
}