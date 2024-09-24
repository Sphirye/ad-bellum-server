package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.MatchPoint
import com.sphirye.springtemplate.service.MatchPointService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MatchPointController {

    @Autowired
    private lateinit var _matchPointService: MatchPointService

    @Paged
    @GetMapping("/match-point")
    fun getMatchPoints(
        @Pager pageRequest: PageRequest,
    ): Page<MatchPoint> {
        return _matchPointService.findAll(pageRequest)
    }


}