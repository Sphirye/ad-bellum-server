package com.sphirye.springtemplate.domain.port.outbound

interface PasswordEncoderPort {
    fun encode(rawPassword: CharSequence): String
    fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean
}
