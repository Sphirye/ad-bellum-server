package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.Penalty
import com.sphirye.springtemplate.service.PenaltyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class PenaltyController {

    @Autowired
    private lateinit var _penaltyService: PenaltyService

    @Paged
    @GetMapping("/penalty")
    fun getPenalties(
        @Pager pageRequest: PageRequest,
        @ModelAttribute penalty: Penalty,
    ): Page<Penalty> {
        return _penaltyService.findAll(penalty, pageRequest)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/penalty")
    fun postPenalty(
        @Validated @RequestBody penalty: Penalty,
    ): Penalty {
        return _penaltyService.create(penalty)
    }

    @PutMapping("/penalty/{id}")
    fun updatePenalty(
        @PathVariable id: Long,
        @Validated @RequestBody penalty: Penalty,
    ): Penalty {
        return _penaltyService.update(id, penalty)
    }

    @DeleteMapping("/penalty/{id}")
    fun delete(@PathVariable id: Long) {
        return _penaltyService.deleteById(id)
    }

}