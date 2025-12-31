package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.ScoreAction
import com.sphirye.springtemplate.repository.ScoreActionRepository
import org.springframework.stereotype.Service

@Service
class ScoreActionService(
    private val _scoreActionRepository: ScoreActionRepository,
): BaseService<ScoreAction, Long>(_scoreActionRepository) {
}