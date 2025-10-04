package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.Group
import com.sphirye.springtemplate.service.GroupService
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
    private val _groupService: GroupService,
) {

    @GetMapping("/group/{id}")
    fun get(@PathVariable id: Long): Group {
        return _groupService.findById(id)
    }

    @Paged
    @GetMapping("/group")
    fun getAll(
        @ModelAttribute example: Group,
        @Pager pageRequest: PageRequest,
    ): Page<Group> {
        return _groupService.findAll(example, pageRequest)
    }

    @PostMapping("/group")
    fun post(@Validated @RequestBody group: Group): Group {
        return _groupService.create(group)
    }

    @PutMapping("/group/{id}")
    fun update(
        @PathVariable id: Long,
        @Validated @RequestBody group: Group,
    ): Group {
        return _groupService.update(id, group)
    }

    @DeleteMapping("/group/{id}")
    fun delete(@PathVariable id: Long) {
        return _groupService.deleteById(id)
    }
}