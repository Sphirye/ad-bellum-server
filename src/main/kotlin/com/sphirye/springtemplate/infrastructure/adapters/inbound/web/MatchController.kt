package com.sphirye.springtemplate.infrastructure.adapters.inbound.web

import com.sphirye.springtemplate.domain.model.Match
import com.sphirye.springtemplate.domain.model.MatchFilter
import com.sphirye.springtemplate.domain.port.inbound.MatchUseCase
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.MatchFinishRequest
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.MatchRequest
import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class MatchController(
    private val matchUseCase: MatchUseCase,
) {

    @GetMapping("/match/{id}")
    fun getMatch(@PathVariable id: Long): Match {
        return matchUseCase.findById(id)
    }

    @Paged
    @GetMapping("/match")
    fun getMatches(
        @ModelAttribute example: MatchFilter,
        @Pager pageRequest: PageRequest,
    ): Page<Match> {
        return matchUseCase.findAll(example, pageRequest)
    }

    @PostMapping("/match")
    @ResponseStatus(HttpStatus.CREATED)
    fun postMatch(
        @Validated @RequestBody match: MatchRequest,
    ): Match {
        return matchUseCase.create(match.toDomain())
    }

    @PutMapping("/match/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @PathVariable id: Long,
        @Validated @RequestBody match: MatchRequest,
    ): Match {
        return matchUseCase.update(id, match.toDomain())
    }

    @PatchMapping("/match/{id}/finish")
    @ResponseStatus(HttpStatus.OK)
    fun finishMatch(
        @PathVariable id: Long,
        @RequestBody match: MatchFinishRequest,
    ): Match {
        return matchUseCase.finish(id, match.toDomain())
    }

    @DeleteMapping("/match/{id}")
    fun delete(@PathVariable id: Long) {
        return matchUseCase.delete(id)
    }
}
