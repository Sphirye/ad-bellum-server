package com.sphirye.springtemplate.application.service

import com.sphirye.shared.exception.exceptions.ResourceNotFoundException
import com.sphirye.springtemplate.domain.model.Group
import com.sphirye.springtemplate.domain.port.inbound.GroupUseCase
import com.sphirye.springtemplate.domain.port.outbound.GroupPersistencePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GroupService(
    private val groupPersistencePort: GroupPersistencePort,
) : GroupUseCase {

    override fun findById(id: Long): Group =
        groupPersistencePort.findById(id) ?: throw ResourceNotFoundException("Resource with id $id does not exists")

    override fun findAll(example: Group?, pageable: Pageable): Page<Group> {
        return if (example != null) {
            groupPersistencePort.findAllByExample(example, pageable)
        } else {
            groupPersistencePort.findAll(pageable)
        }
    }

    override fun create(group: Group): Group =
        groupPersistencePort.save(group)

    override fun update(id: Long, group: Group): Group {
        _validateResourceExistsById(id)
        return groupPersistencePort.save(group.copy(id = id))
    }

    override fun delete(id: Long) {
        _validateResourceExistsById(id)
        groupPersistencePort.deleteById(id)
    }

    private fun _validateResourceExistsById(id: Long) {
        if (!groupPersistencePort.existsById(id)) {
            throw ResourceNotFoundException("Resource with id $id does not exists")
        }
    }
}
