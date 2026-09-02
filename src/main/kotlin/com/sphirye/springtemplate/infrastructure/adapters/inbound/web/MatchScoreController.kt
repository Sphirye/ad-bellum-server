package com.sphirye.springtemplate.infrastructure.adapters.inbound.web

import com.sphirye.springtemplate.domain.model.MatchScore
import com.sphirye.springtemplate.domain.port.inbound.MatchScoreUseCase
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.MatchScoreFilter
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.MatchScoreRequest
import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MatchScoreController(
    private val matchScoreUseCase: MatchScoreUseCase,
) {

    @Paged
    @GetMapping("/match-score")
    fun getScores(
        @Pager pageRequest: PageRequest,
        @ModelAttribute matchScore: MatchScoreFilter,
    ): Page<MatchScore> {
        return matchScoreUseCase.findAll(matchScore.toDomain(), pageRequest)
    }

    @PostMapping("/match-score")
    fun postScore(
        @Validated @RequestBody matchScore: MatchScoreRequest,
    ): MatchScore {
        return matchScoreUseCase.createScore(matchScore.toDomain())
    }

    @PutMapping("/match-score/{id}")
    fun update(
        @PathVariable id: Long,
        @Validated @RequestBody matchScore: MatchScoreRequest,
    ): MatchScore {
        return matchScoreUseCase.update(id, matchScore.toDomain())
    }

    @DeleteMapping("/match-score/{id}")
    fun delete(@PathVariable id: Long) {
        return matchScoreUseCase.delete(id)
    }

}
