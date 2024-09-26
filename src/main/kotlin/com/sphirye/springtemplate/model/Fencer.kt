package com.sphirye.springtemplate.model

import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Entity
class Fencer (
    @Id
    @GeneratedValue
    override var id: Long? = null,

    @field:NotNull
    var name: String? = null,

    var email: String? = null,

): Identifiable<Long>, Serializable, Auditing()