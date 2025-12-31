package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonBackReference

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

    @Column(nullable = false)
    var title: String? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: PenaltyType? = null,

    @Column(nullable = false)
    @field:NotNull
    var value: Int? = null,

): Identifiable<Long>, Serializable, Auditing() {

    enum class PenaltyType {
        TEMPLATE, INSTANCE
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "score_profile_id", nullable = false)
    var scoreProfile: ScoreProfile? = null
}