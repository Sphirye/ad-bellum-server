package com.sphirye.springtemplate.service

import com.sphirye.shared.exception.exceptions.ConflictException
import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Match
import com.sphirye.springtemplate.repository.MatchRepository
import com.sphirye.springtemplate.repository.specification.MatchSpecification
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    override fun findAll(entity: Match?, pageable: Pageable): Page<Match> {
        return if (entity != null) {
            _matchRepository.findAll(MatchSpecification.query(entity), pageable)
        } else {
            _matchRepository.findAll(pageable)
        }
    }

    fun hasMatchFinished(id: Long): Boolean {
        return _matchRepository.existsByIdAndState(id, Match.MatchState.FINISHED)
    }

}