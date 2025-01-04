package com.sphirye.springtemplate.model

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
    @Column(updatable = false, nullable = false)
    var createdDate: LocalDateTime? = null

    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null

    @CreatedBy
    @Column(updatable = false, nullable = false)
    var createdBy: Long? = null

    @LastModifiedBy
    var lastModifiedBy: Long? = null
}