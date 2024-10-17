package com.sphirye.springtemplate.service

import com.sphirye.shared.exception.exceptions.ApplicationException
import com.sphirye.shared.exception.exceptions.ConflictException
import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Match
import com.sphirye.springtemplate.repository.MatchRepository
import org.springframework.stereotype.Service

@Service
class MatchService(
    private val _matchRepository: MatchRepository,
): BaseService<Match, Long>(_matchRepository) {

    override fun beforeUpdate(id: Long, entity: Match): Match {
        if (hasMatchFinished(id)) {
            throw ConflictException("Finished matches cannot be updated.")
        }
        return super.beforeUpdate(id, entity)
    }

    fun hasMatchFinished(id: Long): Boolean {
        return _matchRepository.existsByIdAndState(id, Match.MatchState.FINISHED)
    }

}