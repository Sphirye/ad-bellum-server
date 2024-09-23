package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.Combat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CombatRepository : JpaRepository<Combat, Long> {

}