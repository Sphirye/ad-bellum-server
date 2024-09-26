package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull

@Entity
class MatchScore (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    var type: PointType? = null,

    @field:NotNull
    @ManyToOne
    var scorer: Fencer? = null,

    @field:NotNull
    var timestamp: String? = null,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    var verdict: Verdict? = null,

): Identifiable<Long> {
    enum class PointType {
        CUT, THRUST, SLICE
    }

    enum class Verdict {
        CLEAN, AFTER_BLOW, DOUBLE, NO_EXCHANGE
    }
}