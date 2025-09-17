package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Entity
@Table(name = "score_overrides")
class ScoreOverride (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    var region: MatchScore.RegionType? = null,

    @Column(nullable = false)
    @field:NotNull
    var value: Int? = null,

): Identifiable<Long>, Serializable, Auditing() {

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "action_id", nullable = false)
    var action: ScoreAction? = null

}