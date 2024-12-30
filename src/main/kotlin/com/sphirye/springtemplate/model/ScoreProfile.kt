package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull
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

    @Enumerated(EnumType.STRING)
    @field:NotNull
    var type: ScoreProfileType? = null,

    ): Identifiable<Long>, Serializable, Auditing() {
    enum class ScoreProfileType {
        TEMPLATE, INSTANCE
    }
}