package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Match
import com.sphirye.springtemplate.repository.MatchRepository
import org.springframework.stereotype.Service

@Service
class MatchService(
    private val _matchRepository: MatchRepository,
): BaseService<Match, Long>(_matchRepository) {

}