package com.sphirye.springtemplate.controller

import com.sphirye.shared.web.annotation.Paged
import com.sphirye.shared.web.annotation.Pager
import com.sphirye.springtemplate.model.User
import com.sphirye.springtemplate.service.UserService
import org.springframework.beans.factory.annotation.Autowired
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
class UserController {

    @Autowired
    private lateinit var _userService: UserService

    @Paged
    @GetMapping("/user")
    fun getUsers(
        @ModelAttribute user: User,
        @Pager pageRequest: PageRequest,
    ): Page<User> {
        return _userService.findAll(user, pageRequest)
    }

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Long): User {
        return _userService.findById(id)
    }

    @PostMapping("/user")
    fun postUser(
        @Validated @RequestBody user: User,
    ): User {
        return _userService.create(user)
    }

    @DeleteMapping("/user/{id}")
    fun delete(@PathVariable id: Long) {
        return _userService.deleteById(id)
    }
}