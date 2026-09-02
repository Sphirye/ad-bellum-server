package com.sphirye.springtemplate.domain.model

import java.time.LocalDateTime

data class Penalty(
    val id: Long? = null,
    val scoreId: Long? = null,
    val title: String? = null,
    val type: PenaltyType? = null,
    val value: Int? = null,
    val createdDate: LocalDateTime? = null,
    val lastModifiedDate: LocalDateTime? = null,
    val createdById: Long? = null,
    val lastModifiedBy: Long? = null,
) {
    enum class PenaltyType {
        TEMPLATE, INSTANCE
    }
}
