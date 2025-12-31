package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
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

): Identifiable<Long>, Serializable, Auditing() {

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "score_profile_id", nullable = false)
    var scoreProfile: ScoreProfile? = null

    @JsonManagedReference
    @OneToMany(mappedBy = "action", cascade = [CascadeType.ALL], orphanRemoval = true)
    var overrides: MutableList<ScoreOverride> = mutableListOf()

}