package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.Match
import com.sphirye.springtemplate.model.MatchExample
import com.sphirye.springtemplate.service.MatchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class MatchController {

    @Autowired
    private lateinit var _matchService: MatchService

    @GetMapping("/match/{id}")
    fun getMatch(@PathVariable id: Long): Match {
        return _matchService.findById(id)
    }

    @Paged
    @GetMapping("/match")
    fun getMatches(
        @ModelAttribute example: MatchExample,
        @Pager pageRequest: PageRequest,
    ): Page<Match> {
        return _matchService.findAll(example, pageRequest)
    }

    @PostMapping("/match")
    @ResponseStatus(HttpStatus.CREATED)
    fun postMatch(
        @Validated @RequestBody match: Match,
    ): Match {
        return _matchService.create(match)
    }

    @PutMapping("/match/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @PathVariable id: Long,
        @Validated @RequestBody match: Match,
    ): Match {
        return _matchService.update(id, match)
    }

    @DeleteMapping("/match/{id}")
    fun delete(@PathVariable id: Long) {
        return _matchService.deleteById(id)
    }
}