package com.sphirye.springtemplate.infrastructure.adapters.inbound.web

import com.sphirye.springtemplate.domain.model.User
import com.sphirye.springtemplate.domain.port.inbound.UserUseCase
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.UserFilter
import com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto.UserRequest
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
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userUseCase: UserUseCase,
) {

    @Paged
    @GetMapping("/user")
    fun getUsers(
        @ModelAttribute user: UserFilter,
        @Pager pageRequest: PageRequest,
    ): Page<User> {
        return userUseCase.findAll(user.toDomain(), pageRequest)
    }

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Long): User {
        return userUseCase.findById(id)
    }

    @PostMapping("/user")
    fun postUser(
        @Validated @RequestBody user: UserRequest,
    ): User {
        return userUseCase.create(user.toDomain())
    }

    @DeleteMapping("/user/{id}")
    fun delete(@PathVariable id: Long) {
        return userUseCase.delete(id)
    }
}
