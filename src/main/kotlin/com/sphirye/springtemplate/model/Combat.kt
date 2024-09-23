package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.*

@Entity
class Combat (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    var fencer_1: Fencer? = null,

    var fencer_2: Fencer? = null,

    @Enumerated(EnumType.STRING)
    var state: CombatState? = null,

): Identifiable<Long> {
    enum class CombatState {
        WAITING, IN_PROGRESS, FINISHED
    }
}