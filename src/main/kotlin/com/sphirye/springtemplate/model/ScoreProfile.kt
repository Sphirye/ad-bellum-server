package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.ColumnDefault
import java.io.Serializable

@Entity
class ScoreProfile (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    var name: String? = null,

    @ColumnDefault("0")
    var thrusts: Int? = null,

    @ColumnDefault("0")
    var cuts: Int? = null,

    @ColumnDefault("0")
    var slices: Int? = null,

    @ColumnDefault("0")
    var controls: Int? = null,

    @ColumnDefault("0")
    var dobleoutLimit: Int? = null,

    @ColumnDefault("0")
    var pointsLimit: Int? = null,

    @ColumnDefault("0")
    var timeLimitInSeconds: Int? = null,

    @Enumerated(EnumType.STRING)
    var type: ScoreProfileType? = null,

    ): Identifiable<Long>, Serializable, Auditing() {
    enum class ScoreProfileType {
        TEMPLATE, INSTANCE
    }
}