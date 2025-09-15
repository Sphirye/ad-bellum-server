package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sphirye.shared.utils.Identifiable
import com.sphirye.shared.web.annotation.EntityExists
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ForeignKey
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

    @Column(name = "action_id")
    @field:EntityExists(
        entityName = "Action",
        primaryKey = "id",
    )
    @field:NotNull
    var actionId: Long? = null,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    var region: MatchScore.RegionType? = null,

    @Column(nullable = false)
    @field:NotNull
    var value: Int? = null,

    @Column(name = "score_profile_id", nullable = false)
    var scoreProfileId: Long? = null
): Identifiable<Long>, Serializable, Auditing() {

    @ManyToOne
    @JoinColumn(
        name = "action_id",
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(foreignKeyDefinition = "FOREIGN KEY (action_id) REFERENCES score_actions(id) ON DELETE CASCADE")
    )
    var action: ScoreAction? = null

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "score_profile_id", insertable = false, updatable = false)
    var scoreProfile: ScoreProfile? = null
}