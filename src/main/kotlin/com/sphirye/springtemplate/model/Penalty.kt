package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sphirye.shared.utils.Identifiable
import com.sphirye.shared.web.annotation.EntityExists
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Entity
class Penalty (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    @Column(name = "score")
    @field:EntityExists(
        entityName = "MatchScore",
        primaryKey = "id",
    )
    var scoreId: Long? = null,

    @Column(name = "score_profile")
    @field:EntityExists(
        entityName = "ScoreProfile",
        primaryKey = "id",
    )
    var scoreProfileId: Long? = null,

    @Column(nullable = false)
    var title: String? = null,

    @Column(name = "fencer")
    @field:EntityExists(
        entityName = "Fencer",
        primaryKey = "id",
    )
    var fencerId: Long? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: PenaltyType? = null,

    @Column(nullable = false)
    @field:NotNull
    var value: Int? = null,

    @Column(nullable = false)
    @field:NotNull
    var severity: Int? = null,

): Identifiable<Long>, Serializable, Auditing() {

    enum class PenaltyType {
        TEMPLATE, INSTANCE
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fencer", insertable = false, updatable = false)
    var fencer: Fencer? = null

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "score", insertable = false, updatable = false)
    var score: MatchScore? = null

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "score_profile", insertable = false, updatable = false)
    var scoreProfile: ScoreProfile? = null
}