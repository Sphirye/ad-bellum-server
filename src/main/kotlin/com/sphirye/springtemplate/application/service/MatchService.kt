package com.sphirye.springtemplate.application.service

import com.sphirye.shared.exception.exceptions.ConflictException
import com.sphirye.shared.exception.exceptions.ResourceNotFoundException
import com.sphirye.springtemplate.domain.model.Match
import com.sphirye.springtemplate.domain.model.MatchFilter
import com.sphirye.springtemplate.domain.port.inbound.MatchUseCase
import com.sphirye.springtemplate.domain.port.inbound.ScoreProfileUseCase
import com.sphirye.springtemplate.domain.port.outbound.MatchPersistencePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MatchService(
    private val matchPersistencePort: MatchPersistencePort,
    private val scoreProfileUseCase: ScoreProfileUseCase,
) : MatchUseCase {

    @Transactional
    override fun create(match: Match): Match {
        val scoreProfile = scoreProfileUseCase.instance(match.scoreProfile!!)
        return matchPersistencePort.save(match.copy(scoreProfileId = scoreProfile.id))
    }

    override fun update(id: Long, match: Match): Match {
        _validateResourceExistsById(id)
        if (hasMatchFinished(id)) {
            throw ConflictException("Finished matches cannot be updated.")
        }
        return matchPersistencePort.save(match.copy(id = id))
    }

    override fun finish(id: Long, match: Match): Match {
        val currentMatch = findById(id)

        val updated = currentMatch.copy(
            state = match.state,
            resolution = match.resolution,
            winnerFencerId = match.winnerFencerId,
        )

        return update(id, updated)
    }

    override fun findById(id: Long): Match =
        matchPersistencePort.findById(id) ?: throw ResourceNotFoundException("Resource with id $id does not exists")

    override fun findAll(filter: MatchFilter?, pageable: Pageable): Page<Match> {
        return if (filter != null) {
            matchPersistencePort.findAllByFilter(filter, pageable)
        } else {
            matchPersistencePort.findAll(pageable)
        }
    }

    override fun hasMatchFinished(id: Long): Boolean =
        matchPersistencePort.existsByIdAndState(id, Match.MatchState.FINISHED)

    override fun delete(id: Long) {
        _validateResourceExistsById(id)
        matchPersistencePort.deleteById(id)
    }

    private fun _validateResourceExistsById(id: Long) {
        if (!matchPersistencePort.existsById(id)) {
            throw ResourceNotFoundException("Resource with id $id does not exists")
        }
    }
}
