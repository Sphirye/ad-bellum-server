package com.sphirye.springtemplate.infrastructure.adapters.inbound.web

import com.sphirye.springtemplate.domain.model.Fencer
import com.sphirye.springtemplate.domain.port.inbound.FencerUseCase
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.FencerRequest
import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class FencerController(
    private val fencerUseCase: FencerUseCase,
) {

    @GetMapping("/fencer/{id}")
    fun getFencer(@PathVariable id: Long): Fencer {
        return fencerUseCase.findById(id)
    }

    @Paged
    @GetMapping("/fencer")
    fun getFencers(
        @Pager pageRequest: PageRequest,
        @RequestParam(required = false) search: String?,
    ): Page<Fencer> {
        return fencerUseCase.searchByName(search, pageRequest)
    }

    @GetMapping("/fencer/{id}/stats")
    fun getFencerStats(@PathVariable id: Long): com.sphirye.springtemplate.application.dto.FencerStats {
        return fencerUseCase.getStats(id)
    }

    @PostMapping("/fencer")
    @ResponseStatus(HttpStatus.CREATED)
    fun postFencer(
        @Validated @RequestBody fencer: FencerRequest,
    ): Fencer {
        return fencerUseCase.create(fencer.toDomain())
    }

    @PutMapping("/fencer/{id}")
    fun update(
        @PathVariable id: Long,
        @Validated @RequestBody fencer: FencerRequest,
    ): Fencer {
        return fencerUseCase.update(id, fencer.toDomain())
    }

    @DeleteMapping("/fencer/{id}")
    fun delete(@PathVariable id: Long) {
        return fencerUseCase.delete(id)
    }
}
