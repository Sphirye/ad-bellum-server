package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sphirye.shared.utils.Identifiable
import com.sphirye.shared.web.annotation.EntityExists
import jakarta.persistence.*
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

    @Column(name = "match_id", nullable = false)
    @field:EntityExists(
        entityName = "Match",
        primaryKey = "id",
    )
    var matchId: Long? = null,

): Identifiable<Long> {
    enum class PointType {
        CUT, THRUST, SLICE
    }

    enum class Verdict {
        POINT, AFTER_BLOW, DOUBLE, NO_EXCHANGE
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "match_id", insertable = false, updatable = false)
    var match: Match? = null

}