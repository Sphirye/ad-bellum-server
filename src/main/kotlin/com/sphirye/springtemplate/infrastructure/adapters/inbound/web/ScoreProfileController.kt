package com.sphirye.springtemplate.infrastructure.adapters.inbound.web

import com.sphirye.springtemplate.domain.model.ScoreProfile
import com.sphirye.springtemplate.domain.port.inbound.ScoreProfileUseCase
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.ScoreProfileFilter
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.ScoreProfileRequest
import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ScoreProfileController(
    private val scoreProfileUseCase: ScoreProfileUseCase,
) {

    @GetMapping("/score-profile/{id}")
    fun getScoreProfile(@PathVariable id: Long): ScoreProfile {
        return scoreProfileUseCase.findById(id)
    }

    @Paged
    @GetMapping("/score-profile")
    fun getScoreProfile(
        @ModelAttribute profileScore: ScoreProfileFilter,
        @Pager pageRequest: PageRequest,
    ): Page<ScoreProfile> {
        return scoreProfileUseCase.findAll(profileScore.toDomain(), pageRequest)
    }

    @PostMapping("/score-profile")
    fun postScoreProfile(
        @Validated @RequestBody scoreProfileScore: ScoreProfileRequest,
    ): ScoreProfile {
        return scoreProfileUseCase.createProfileScore(scoreProfileScore.toDomain())
    }

    @PutMapping("/score-profile/{id}")
    fun putScoreProfile(
        @PathVariable id: Long,
        @Validated @RequestBody scoreProfileScore: ScoreProfileRequest,
    ): ScoreProfile {
        return scoreProfileUseCase.update(id, scoreProfileScore.toDomain())
    }

    @PatchMapping("/score-profile/{id}/time-left")
    fun patchTimeLeft(@PathVariable id: Long, @RequestParam timeLeft: Int): ScoreProfile {
        return scoreProfileUseCase.updateTimeLeft(id, timeLeft)
    }

    @PatchMapping("/score-profile/{id}/elapsed-time")
    fun patchElapsedTime(@PathVariable id: Long, @RequestParam elapsedTime: Int): ScoreProfile {
        return scoreProfileUseCase.updateElapsedTime(id, elapsedTime)
    }

    @DeleteMapping("/score-profile/{id}")
    fun deleteScoreProfile(@PathVariable id: Long) {
        return scoreProfileUseCase.delete(id)
    }
}
