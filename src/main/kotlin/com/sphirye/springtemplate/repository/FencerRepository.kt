package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.Fencer
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FencerRepository : JpaRepository<Fencer, Long> {

    fun findAllByNameIgnoreCaseContaining(name: String, pageRequest: PageRequest): Page<Fencer>

}