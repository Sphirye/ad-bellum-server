package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.Fencer
import com.sphirye.springtemplate.service.FencerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FencerController {

    @Autowired
    private lateinit var _fencerService: FencerService

    @Paged
    @GetMapping("/fencer")
    fun getFencers(
        @Pager pageRequest: PageRequest,
        @RequestParam(required = false) search: String?,
    ): Page<Fencer> {
        return _fencerService.searchByName(search, pageRequest)
    }
}