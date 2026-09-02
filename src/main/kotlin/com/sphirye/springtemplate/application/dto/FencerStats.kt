package com.sphirye.springtemplate.application.dto

import java.time.LocalDateTime

data class FencerStats(
    val totalPoints: Int? = null,
    val totalThrusts: Int? = null,
    val totalCuts: Int? = null,
    val totalSlices: Int? = null,
    val totalDoubles: Int? = null,
    val totalControls: Int? = null,
    val totalAfterblows: Int? = null,
    val fromDate: LocalDateTime? = null,
)
