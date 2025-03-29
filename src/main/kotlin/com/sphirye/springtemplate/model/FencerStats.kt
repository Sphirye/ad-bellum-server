package com.sphirye.springtemplate.model

import java.time.LocalDateTime

interface FencerStats {
    val totalPoints: Int?

    val totalThrusts: Int?

    val totalCuts: Int?

    val totalSlices: Int?

    val totalDoubles: Int?

    val totalControls: Int?

    val totalAfterblows: Int?

    val fromDate: LocalDateTime?
}