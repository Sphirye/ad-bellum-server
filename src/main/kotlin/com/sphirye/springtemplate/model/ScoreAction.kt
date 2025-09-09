package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "score_actions")
class ScoreAction (

    @Id
    @GeneratedValue
    override var id: Long? = null,

    @Column(nullable = false)
    var title: String? = null,

    @Column(nullable = false)
    var value: Int? = null,

    @Column(name = "score_profile_id", nullable = false)
    var scoreProfileId: Long? = null

): Identifiable<Long>, Serializable, Auditing() {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "score_profile_id", insertable = false, updatable = false)
    var scoreProfile: ScoreProfile? = null

}