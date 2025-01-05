package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Entity
class ScoreOverride (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    var pointType: MatchScore.PointType? = null,

    @Enumerated(EnumType.STRING)
    @field:NotNull
    var region: MatchScore.RegionType? = null,

    @Column(nullable = false)
    @field:NotNull
    var value: Int? = null,
): Identifiable<Long>, Serializable, Auditing()