package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.MatchScore
import com.sphirye.springtemplate.repository.MatchScoreRepository
import org.springframework.stereotype.Service

@Service
class MatchScoreService(
    private val _matchScoreRepository: MatchScoreRepository
): BaseService<MatchScore, Long>(_matchScoreRepository) {
}