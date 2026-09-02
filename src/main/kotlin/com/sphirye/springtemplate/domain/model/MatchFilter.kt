package com.sphirye.springtemplate.domain.model

data class MatchFilter(
    var fencer_1_id: Long? = null,
    var fencer_2_id: Long? = null,
    var state: Match.MatchState? = null,
    var dateRange: DateRange? = null,
    var createdBy: String? = null,
    var groupId: String? = null,
)
