package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.Match
import com.sphirye.springtemplate.service.MatchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MatchController {

    @Autowired
    private lateinit var _matchService: MatchService

    @Paged
    @GetMapping("/match")
    fun getMatches(
        @Pager pageRequest: PageRequest,
    ): Page<Match> {
        return _matchService.findAll(pageRequest)
    }

}