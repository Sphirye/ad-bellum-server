package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sphirye.shared.utils.Identifiable
import com.sphirye.shared.web.annotation.EntityExists
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Entity
@Table(name = "match_scores")
class MatchScore (
    @Id
    @GeneratedValue
    override var id: Long? = null,


    @Column(name = "scorer_id")
    @field:EntityExists(
        entityName = "Fencer",
        primaryKey = "id",
    )
    var scorerId: Long? = null,

    @Column(name = "action_id")
    @field:EntityExists(
        entityName = "ScoreAction",
        primaryKey = "id",
    )
    var actionId: Long? = null,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    var verdict: Verdict? = null,

    @Column(name = "match_id", nullable = false)
    @field:EntityExists(
        entityName = "Match",
        primaryKey = "id",
    )
    var matchId: Long? = null,

    var afterblow: Boolean? = null,

    var control: Boolean? = null,

    @Enumerated(EnumType.STRING)
    var region: RegionType? = null,

): Identifiable<Long>, Serializable, Auditing() {

    enum class Verdict {
        POINT, DOUBLE, NO_EXCHANGE, NO_QUALITY
    }

    enum class RegionType {
        HEAD, ARM, HAND, CHEST, LEG
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "match_id", insertable = false, updatable = false)
    var match: Match? = null

    @ManyToOne
    @JoinColumn(name = "scorer_id", insertable = false, updatable = false)
    var scorer: Fencer? = null

    @OneToOne
    @JoinColumn(name = "action_id", insertable = false, updatable = false)
    var action: ScoreAction? = null

    @OneToMany(mappedBy = "score", orphanRemoval = true)
    var penaltyRecords: MutableList<PenaltyRecords> = mutableListOf()
}