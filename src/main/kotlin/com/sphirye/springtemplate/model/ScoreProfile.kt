package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import java.io.Serializable

@Entity
@Table(name = "score_profiles")
class ScoreProfile (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    var name: String? = null,

    @ColumnDefault("0")
    var controls: Int? = null,

    @ColumnDefault("0")
    var afterblow: Int? = null,

    @ColumnDefault("0")
    var dobleoutLimit: Int? = null,

    var pointsLimit: Int? = null,

    var hasDobleoutLimit: Boolean? = false,

    var timed: Boolean? = false,

    var timeLimitInSeconds: Int? = null,

    var timeLeft: Int? = null,

    @Enumerated(EnumType.STRING)
    var type: ScoreProfileType? = null,

): Identifiable<Long>, Serializable, Auditing() {

    enum class ScoreProfileType {
        TEMPLATE, INSTANCE
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "scoreProfile", cascade = [CascadeType.ALL], orphanRemoval = true)
    var penalties: MutableList<Penalty>? = null

    @JsonManagedReference
    @OneToMany(mappedBy = "scoreProfile", cascade = [CascadeType.ALL], orphanRemoval = true)
    var actions: MutableList<ScoreAction>? = mutableListOf()

    fun setChildrenRelationships() {
        this.actions?.forEach { it.scoreProfile = this }
        this.penalties?.forEach { it.scoreProfile = this }
    }

    fun removeChildrenIds() {
        this.penalties?.forEach { it.id = null }
        this.actions?.forEach {
            it.overrides.forEach { it.id = null }
            it.id = null
        }
        this.setChildrenRelationships()
    }

}