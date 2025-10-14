package com.sphirye.springtemplate.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class Auditing : Serializable {

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "created_date", updatable = false, nullable = false)
    var createdDate: LocalDateTime? = null

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null

    @CreatedBy
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "created_by", updatable = false)
    var createdById: Long? = null

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    var createdBy: User? = null

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedBy
    var lastModifiedBy: Long? = null
}