package com.sphirye.springtemplate.domain.port.inbound

import com.sphirye.springtemplate.domain.model.Group
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GroupUseCase {
    fun findById(id: Long): Group
    fun findAll(example: Group?, pageable: Pageable): Page<Group>
    fun create(group: Group): Group
    fun update(id: Long, group: Group): Group
    fun delete(id: Long)
}
