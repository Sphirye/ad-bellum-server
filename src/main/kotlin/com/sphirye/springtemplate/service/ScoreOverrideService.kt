package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.ScoreOverride
import com.sphirye.springtemplate.repository.ScoreOverrideRepository
import org.springframework.stereotype.Service

@Service
class ScoreOverrideService(
    private val _scoreOverrideRepository: ScoreOverrideRepository,
): BaseService<ScoreOverride, Long>(_scoreOverrideRepository) {
}