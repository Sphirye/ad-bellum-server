package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.MatchScore
import com.sphirye.springtemplate.service.MatchScoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class MatchScoreController {

    @Autowired
    private lateinit var _matchScoreService: MatchScoreService

    @Paged
    @GetMapping("/match-score")
    fun getScores(
        @Pager pageRequest: PageRequest,
        @ModelAttribute matchScore: MatchScore,
    ): Page<MatchScore> {
        return _matchScoreService.findAll(matchScore, pageRequest)
    }

    @PostMapping("/match-score")
    fun postScore(
        @Validated @RequestBody matchScore: MatchScore,
    ): MatchScore {
        return _matchScoreService.create(matchScore)
    }

    @PutMapping("/match-score/{id}")
    fun update(
        @PathVariable id: Long,
        @Validated @RequestBody matchScore: MatchScore,
    ): MatchScore {
        return _matchScoreService.update(id, matchScore)
    }

}