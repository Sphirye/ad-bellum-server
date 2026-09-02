package com.sphirye.springtemplate.infrastructure.adapters.inbound.web

import com.sphirye.springtemplate.domain.model.Group
import com.sphirye.springtemplate.domain.port.inbound.GroupUseCase
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.GroupFilter
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.GroupRequest
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
class GroupController(
    private val groupUseCase: GroupUseCase,
) {

    @GetMapping("/group/{id}")
    fun get(@PathVariable id: Long): Group {
        return groupUseCase.findById(id)
    }

    @Paged
    @GetMapping("/group")
    fun getAll(
        @ModelAttribute example: GroupFilter,
        @Pager pageRequest: PageRequest,
    ): Page<Group> {
        return groupUseCase.findAll(example.toDomain(), pageRequest)
    }

    @PostMapping("/group")
    fun post(@Validated @RequestBody group: GroupRequest): Group {
        return groupUseCase.create(group.toDomain())
    }

    @PutMapping("/group/{id}")
    fun update(
        @PathVariable id: Long,
        @Validated @RequestBody group: GroupRequest,
    ): Group {
        return groupUseCase.update(id, group.toDomain())
    }

    @DeleteMapping("/group/{id}")
    fun delete(@PathVariable id: Long) {
        return groupUseCase.delete(id)
    }
}
