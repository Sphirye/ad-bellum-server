package com.sphirye.springtemplate.application.service

import com.sphirye.shared.exception.exceptions.ConflictException
import com.sphirye.springtemplate.domain.model.MatchScore
import com.sphirye.springtemplate.domain.port.inbound.MatchScoreUseCase
import com.sphirye.springtemplate.domain.port.inbound.MatchUseCase
import com.sphirye.springtemplate.domain.port.inbound.PenaltyRecordsUseCase
import com.sphirye.springtemplate.domain.port.outbound.MatchScorePersistencePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MatchScoreService(
    private val matchScorePersistencePort: MatchScorePersistencePort,
    private val matchUseCase: MatchUseCase,
    private val penaltyRecordsUseCase: PenaltyRecordsUseCase,
) : MatchScoreUseCase {

    @Transactional
    override fun createScore(score: MatchScore): MatchScore {
        _validateMatchState(score.matchId!!)

        val savedScore = matchScorePersistencePort.save(score)

        score.penaltyRecords.forEach {
            penaltyRecordsUseCase.create(it.copy(scoreId = savedScore.id))
        }

        return savedScore
    }

    override fun findAll(example: MatchScore?, pageable: Pageable): Page<MatchScore> {
        return if (example != null) {
            matchScorePersistencePort.findAllByExample(example, pageable)
        } else {
            matchScorePersistencePort.findAll(pageable)
        }
    }

    override fun update(id: Long, score: MatchScore): MatchScore {
        _validateMatchState(score.matchId!!)
        return matchScorePersistencePort.save(score.copy(id = id))
    }

    override fun delete(id: Long) {
        _validateMatchState(id)
        matchScorePersistencePort.deleteById(id)
    }

    private fun _validateMatchState(id: Long) {
        if (matchUseCase.hasMatchFinished(id)) {
            throw ConflictException("Scores from finished matches cannot be modified.")
        }
    }
}
