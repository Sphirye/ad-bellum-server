package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import com.sphirye.shared.web.annotation.EntityExists
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import net.minidev.json.annotate.JsonIgnore
import java.io.Serializable

/**
 * Intermediary class to record which penalties will
 * be assigned in which score and which fencer.
 */
@Entity
@Table(name = "penalty_records")
class PenaltyRecords (

    @Id
    @GeneratedValue
    override var id: Long? = null,

    @Column(name = "score_id")
    @field:NotNull
    @field:EntityExists(entityName = "MatchScore")
    var scoreId: Long? = null,

    @Column(name = "penalty_id")
    @field:NotNull
    @field:EntityExists(entityName = "Penalty")
    var penaltyId: Long? = null,

    @Column(name = "fencer_id")
    @field:NotNull
    @field:EntityExists(entityName = "Fencer")
    var fencerId: Long? = null,

): Identifiable<Long> ,Serializable {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "score_id", insertable = false, updatable = false)
    var score: MatchScore? = null

    @ManyToOne
    @JoinColumn(name = "penalty_id", insertable = false, updatable = false)
    var penalty: Penalty? = null

    @ManyToOne
    @JoinColumn(name = "fencer_id", insertable = false, updatable = false)
    var fencer: Fencer? = null

}