package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.ProfileScore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileScoreRepository : JpaRepository<ProfileScore, Long> {
}