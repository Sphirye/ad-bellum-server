package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.io.Serializable

@Entity
class ScoreProfile (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    var name: String? = null,

    var thrusts: Int? = 0,

    var cuts: Int? = 0,

    var slices: Int? = 0,

    var controls: Int? = 0,

    var dobleoutLimit: Int? = 0,

    var pointsLimit: Int? = 0,

    var timeLimitInSeconds: Int? = 0,

    @Enumerated(EnumType.STRING)
    var type: ScoreProfileType? = null,

    ): Identifiable<Long>, Serializable, Auditing() {
    enum class ScoreProfileType {
        TEMPLATE, INSTANCE
    }
}