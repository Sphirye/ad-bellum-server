package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository : JpaRepository<Group, Long> {
}