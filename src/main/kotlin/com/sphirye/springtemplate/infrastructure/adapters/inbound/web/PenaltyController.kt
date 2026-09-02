package com.sphirye.springtemplate.infrastructure.adapters.inbound.web

import com.sphirye.springtemplate.domain.model.Penalty
import com.sphirye.springtemplate.domain.port.inbound.PenaltyUseCase
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.PenaltyFilter
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.PenaltyRequest
import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
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
class PenaltyController(
    private val penaltyUseCase: PenaltyUseCase,
) {

    @Paged
    @GetMapping("/penalty")
    fun getPenalties(
        @Pager pageRequest: PageRequest,
        @ModelAttribute penalty: PenaltyFilter,
    ): Page<Penalty> {
        return penaltyUseCase.findAll(penalty.toDomain(), pageRequest)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/penalty")
    fun postPenalty(
        @Validated @RequestBody penalty: PenaltyRequest,
    ): Penalty {
        return penaltyUseCase.create(penalty.toDomain())
    }

    @PutMapping("/penalty/{id}")
    fun updatePenalty(
        @PathVariable id: Long,
        @Validated @RequestBody penalty: PenaltyRequest,
    ): Penalty {
        return penaltyUseCase.update(id, penalty.toDomain())
    }

    @DeleteMapping("/penalty/{id}")
    fun delete(@PathVariable id: Long) {
        return penaltyUseCase.delete(id)
    }

}
