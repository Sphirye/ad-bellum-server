package com.sphirye.springtemplate.application.dto

import com.sphirye.springtemplate.domain.model.User

class Session(
    var token: String,
    var user: User,
)
