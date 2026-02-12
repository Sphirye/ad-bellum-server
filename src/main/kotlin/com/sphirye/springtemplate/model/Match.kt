package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import com.sphirye.shared.web.annotation.EntityExists
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import net.minidev.json.annotate.JsonIgnore
import java.io.Serializable

@Entity
@Table(name = "matches")
class Match (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    @Column(name = "fencer_1", nullable = false)
    @field:EntityExists(
        entityName = "Fencer",
        primaryKey = "id",
    )
    @field:NotNull
    var fencer_1_id: Long? = null,

    @Column(name = "fencer_2", nullable = false)
    @field:EntityExists(
        entityName = "Fencer",
        primaryKey = "id",
    )
    @field:NotNull
    var fencer_2_id: Long? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var state: MatchState? = null,

    @Enumerated(EnumType.STRING)
    var resolution: MatchResolution? = null,

    @Column(name = "score_profile", nullable = false)
    @field:EntityExists(
        entityName = "ScoreProfile",
        primaryKey = "id",
    )
    var scoreProfileId: Long? = null,

    @Column(name = "group_id")
    @field:EntityExists(
        entityName = "Group",
        primaryKey = "id"
    )
    var groupId: Long? = null,

    @Column(name = "winner_fencer_id")
    @field:EntityExists(
        entityName = "Fencer",
        primaryKey = "id",
    )
    var winnerFencerId: Long? = null,

): Identifiable<Long>, Serializable, Auditing() {
    enum class MatchState {
        WAITING, IN_PROGRESS, FINISHED
    }

    enum class MatchResolution {
        POINTS_REACHED,
        DOUBLE_OUT,
        TIME_OUT,
        WITHDRAWAL,
        DISQUALIFICATION,
        INJURY,
        OTHER,
    }

    @ManyToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    var group: Group? = null

    @ManyToOne
    @JoinColumn(name = "score_profile", insertable = false, updatable = false, nullable = false)
    var scoreProfile: ScoreProfile? = null

    @OneToMany(mappedBy = "match")
    var scores: List<MatchScore>? = null

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fencer_1", insertable = false, updatable = false)
    var fencer_1: Fencer? = null

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fencer_2", insertable = false, updatable = false)
    var fencer_2: Fencer? = null

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "winner_fencer_id", insertable = false, updatable = false)
    var winnerFencer: Fencer? = null

}