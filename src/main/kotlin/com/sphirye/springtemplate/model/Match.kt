package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.*

@Entity
class Match (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "fencer_1_id")
    var fencer_1: Fencer? = null,

    @ManyToOne
    @JoinColumn(name = "fencer_2_id")
    var fencer_2: Fencer? = null,

    @Enumerated(EnumType.STRING)
    var state: MatchState? = null,

): Identifiable<Long> {
    enum class MatchState {
        WAITING, IN_PROGRESS, FINISHED
    }
}