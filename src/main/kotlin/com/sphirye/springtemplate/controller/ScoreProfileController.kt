package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.ProfileScore
import com.sphirye.springtemplate.service.ScoreProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ScoreProfileController {

    @Autowired
    private lateinit var _profileScoreService: ScoreProfileService

    @GetMapping("/score-profile/{id}")
    fun getScoreProfile(@PathVariable id: Long): ProfileScore {
        return _profileScoreService.findById(id)
    }

    @Paged
    @GetMapping("/score-profile")
    fun getScoreProfile(
        @ModelAttribute profileScore: ProfileScore,
        @Pager pageRequest: PageRequest,
    ): Page<ProfileScore> {
        return _profileScoreService.findAll(profileScore, pageRequest)
    }

    @PostMapping("/score-profile")
    fun postScoreProfile(
        @Validated @RequestBody scoreProfileScore: ProfileScore,
    ): ProfileScore {
        return _profileScoreService.create(scoreProfileScore)
    }
}