package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.MatchPoint
import com.sphirye.springtemplate.repository.MatchPointRepository
import org.springframework.stereotype.Service

@Service
class MatchPointService(
    private val _matchPointRepository: MatchPointRepository
): BaseService<MatchPoint, Long>(_matchPointRepository) {
}