package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Entity
@Table(name = "groups")
class Group (
    @Id
    @GeneratedValue
    override var id: Long?,

    @Column(name = "title", nullable = false)
    @field:NotNull
    var title: String? = null,

): Identifiable<Long>, Serializable, Auditing() {

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    var matches: MutableList<Match>? = null

}