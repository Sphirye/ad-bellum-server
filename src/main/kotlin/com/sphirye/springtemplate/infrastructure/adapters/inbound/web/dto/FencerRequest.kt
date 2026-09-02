package com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto

import com.sphirye.springtemplate.domain.model.Fencer
import jakarta.validation.constraints.NotNull

data class FencerRequest(
    @field:NotNull
    val name: String? = null,
    val email: String? = null,
) {
    fun toDomain(): Fencer = Fencer(
        name = name,
        email = email,
    )
}
