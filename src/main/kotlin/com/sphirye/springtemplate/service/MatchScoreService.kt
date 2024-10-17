package com.sphirye.springtemplate.service

import com.sphirye.shared.exception.exceptions.ConflictException
import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.MatchScore
import com.sphirye.springtemplate.repository.MatchScoreRepository
import org.springframework.stereotype.Service

@Service
class MatchScoreService(
    private val _matchScoreRepository: MatchScoreRepository,
    private val _matchService: MatchService,
): BaseService<MatchScore, Long>(_matchScoreRepository) {

    override fun beforeCreate(entity: MatchScore): MatchScore {
        _validateMatchState(entity.matchId!!)
        return entity
    }

    override fun beforeUpdate(id: Long, entity: MatchScore): MatchScore {
        _validateMatchState(entity.matchId!!)
        return entity
    }

    private fun _validateMatchState(id: Long) {
        if (_matchService.hasMatchFinished(id)) {
            throw ConflictException("Scores from finished matches cannot be updated.")
        }
    }

}