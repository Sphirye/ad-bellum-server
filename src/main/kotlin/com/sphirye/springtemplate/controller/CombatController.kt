package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.Combat
import com.sphirye.springtemplate.service.CombatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CombatController {

    @Autowired
    private lateinit var _combatService: CombatService

    @Paged
    @GetMapping("/combat")
    fun getCombats(
        @Pager pageRequest: PageRequest,
    ): Page<Combat> {
        return _combatService.findAll(pageRequest)
    }

}