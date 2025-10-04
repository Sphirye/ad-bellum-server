package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Group
import com.sphirye.springtemplate.repository.GroupRepository
import org.springframework.stereotype.Service

@Service
class GroupService(
    private val _groupRepository: GroupRepository
): BaseService<Group, Long>(_groupRepository) {
}