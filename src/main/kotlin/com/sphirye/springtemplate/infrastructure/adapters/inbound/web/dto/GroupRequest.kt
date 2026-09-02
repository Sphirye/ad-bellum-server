package com.sphirye.springtemplate.infrastructure.adapters.inbound.web.dto

import com.sphirye.springtemplate.domain.model.Group
import jakarta.validation.constraints.NotNull

data class GroupRequest(
    @field:NotNull
    val title: String? = null,
) {
    fun toDomain(): Group = Group(
        title = title,
    )
}

class GroupFilter(
    var title: String? = null,
) {
    fun toDomain(): Group = Group(
        title = title,
    )
}
