package com.sphirye.springtemplate.service

import com.sphirye.shared.utils.BaseService
import com.sphirye.springtemplate.model.Combat
import com.sphirye.springtemplate.repository.CombatRepository
import org.springframework.stereotype.Service

@Service
class CombatService(
    private val _combatRepository: CombatRepository,
): BaseService<Combat, Long>(_combatRepository) {

}