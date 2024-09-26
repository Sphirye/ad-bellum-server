package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.MatchScore
import com.sphirye.springtemplate.service.MatchScoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MatchScoreController {

    @Autowired
    private lateinit var _matchScoreService: MatchScoreService

    @Paged
    @GetMapping("/match-score")
    fun getMatchScores(
        @Pager pageRequest: PageRequest,
    ): Page<MatchScore> {
        return _matchScoreService.findAll(pageRequest)
    }


}