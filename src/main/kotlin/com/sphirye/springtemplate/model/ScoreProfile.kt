package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity
class ScoreProfile (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    @field:NotNull
    var name: String? = null,

    @field:NotNull
    var thrusts: Int? = null,

    @field:NotNull
    var cuts: Int? = null,

    @field:NotNull
    var slices: Int? = null,

    @field:NotNull
    var controls: Int? = null,

    @field:NotNull
    var dobleoutLimit: Int? = null,

    @field:NotNull
    var pointsLimit: Int? = null,

    @field:NotNull
    var timeLimitInSeconds: Int? = null,

    @Column(nullable = false)
    var base: Boolean? = false,

): Identifiable<Long>, Serializable, Auditing()