package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import com.sphirye.shared.web.annotation.EntityExists
import jakarta.persistence.*
import net.minidev.json.annotate.JsonIgnore
import java.io.Serializable

@Entity
class Match (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    @Column(name = "fencer_1", nullable = false)
    @field:EntityExists(
        entityName = "Fencer",
        primaryKey = "id",
    )
    var fencer_1_id: Long? = null,

    @Column(name = "fencer_2", nullable = false)
    @field:EntityExists(
        entityName = "Fencer",
        primaryKey = "id",
    )
    var fencer_2_id: Long? = null,

    @Enumerated(EnumType.STRING)
    var state: MatchState? = null,

): Identifiable<Long>, Serializable {
    enum class MatchState {
        WAITING, IN_PROGRESS, FINISHED
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fencer_1", insertable = false, updatable = false)
    var fencer_1: Fencer? = null

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fencer_2", insertable = false, updatable = false)
    var fencer_2: Fencer? = null

}