package com.sphirye.springtemplate.service

import com.sphirye.shared.exception.exceptions.ConflictException
import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Match
import com.sphirye.springtemplate.repository.MatchRepository
import com.sphirye.springtemplate.repository.specification.MatchSpecification
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MatchService(
    private val _matchRepository: MatchRepository,
    private val _scoreProfileService: ScoreProfileService,
): BaseService<Match, Long>(_matchRepository) {

    @Transactional
    override fun beforeCreate(entity: Match): Match {
        _scoreProfileService.instance(entity.scoreProfile!!)
        return entity
    }

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