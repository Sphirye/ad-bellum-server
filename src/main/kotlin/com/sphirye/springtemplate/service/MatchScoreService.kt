package com.sphirye.springtemplate.service

import com.sphirye.shared.exception.exceptions.ConflictException
import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.MatchScore
import com.sphirye.springtemplate.repository.MatchScoreRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MatchScoreService(
    private val _matchScoreRepository: MatchScoreRepository,
    private val _matchService: MatchService,
    private val _penaltyRecordService: PenaltyRecordsService,
): BaseService<MatchScore, Long>(_matchScoreRepository) {

    @Transactional
    fun createScore(score: MatchScore): MatchScore {
        return create(score)
    }

    override fun beforeCreate(entity: MatchScore): MatchScore {
        _validateMatchState(entity.matchId!!)
        return entity
    }

    override fun afterCreated(entity: MatchScore, savedEntity: MatchScore) {
        entity.penaltyRecords.forEach {
            it.scoreId = savedEntity.id
            _penaltyRecordService.create(it)
        }
    }

    override fun beforeUpdate(id: Long, entity: MatchScore): MatchScore {
        _validateMatchState(entity.matchId!!)
        return entity
    }

    override fun beforeDelete(id: Long) {
        _validateMatchState(id)
    }

    private fun _validateMatchState(id: Long) {
        if (_matchService.hasMatchFinished(id)) {
            throw ConflictException("Scores from finished matches cannot be modified.")
        }
    }

}