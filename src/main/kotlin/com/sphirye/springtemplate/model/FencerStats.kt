package com.sphirye.springtemplate.model

import java.time.LocalDateTime

interface FencerStats {

    fun getTotalPoints(): Int?

    fun getTotalThrusts(): Int?

    fun getTotalCuts(): Int?

    fun getTotalSlices(): Int?

    fun getTotalDoubles(): Int?

    fun getTotalControls(): Int?

    fun getTotalAfterblows(): Int?

    fun getFromDate(): LocalDateTime?

}