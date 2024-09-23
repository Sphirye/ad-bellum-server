package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.Combat
import org.springframework.data.jpa.repository.JpaRepository

interface CombatRepository : JpaRepository<Combat, Long> {

}