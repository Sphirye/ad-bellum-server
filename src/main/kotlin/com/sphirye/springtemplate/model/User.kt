package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sphirye.shared.utils.Identifiable
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "_user")
class User(
    @Id
    @GeneratedValue
    override var id: Long? = null,

    @field:NotNull
    var username: String? = null,

    @field:NotNull
    @field:Email
    var email: String? = null,

    @field:NotNull
    @JsonIgnore
    var password: String? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "rel_user_authority",
        inverseJoinColumns = [JoinColumn(name = "authority_id", referencedColumnName = "id")],
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
    )
    var authorities: MutableSet<Authority> = mutableSetOf(),
): Identifiable<Long>