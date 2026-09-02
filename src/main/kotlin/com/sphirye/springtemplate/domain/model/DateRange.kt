package com.sphirye.springtemplate.domain.model

import java.sql.Timestamp

data class DateRange(
    val from: Timestamp? = null,
    val until: Timestamp? = null,
)
