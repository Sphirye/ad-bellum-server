package com.sphirye.springtemplate.domain.port.outbound

import com.sphirye.springtemplate.domain.model.Group
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GroupPersistencePort {
    fun findById(id: Long): Group?
    fun findAll(pageable: Pageable): Page<Group>
    fun findAllByExample(example: Group, pageable: Pageable): Page<Group>
    fun save(group: Group): Group
    fun deleteById(id: Long)
    fun existsById(id: Long): Boolean
}
