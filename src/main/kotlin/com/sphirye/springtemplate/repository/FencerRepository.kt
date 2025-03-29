package com.sphirye.springtemplate.repository

import com.sphirye.springtemplate.model.Fencer
import com.sphirye.springtemplate.model.FencerStats
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FencerRepository : JpaRepository<Fencer, Long> {

    fun findAllByNameIgnoreCaseContaining(name: String, pageRequest: PageRequest): Page<Fencer>

    @Query(CALCULATE_FENCER_STATS, nativeQuery = true)
    fun getFencerStats(
        @Param("fencerId") fencerId: Long,
    ): FencerStats

    companion object {
        // TODO: consider fromDate param from FencerStats.kt
        const val CALCULATE_FENCER_STATS = """
            SELECT
                SUM(CASE WHEN ms.point_type = 'THRUST' AND ms.scorer_id = :fencerId THEN 1 ELSE 0 END) as totalThrusts,
                SUM(CASE WHEN ms.point_type = 'CUT' AND ms.scorer_id = :fencerId THEN 1 ELSE 0 END) as totalCuts,
                SUM(CASE WHEN ms.point_type = 'SLICE' AND ms.scorer_id = :fencerId THEN 1 ELSE 0 END) as totalSlices,
                SUM(CASE WHEN ms.verdict = 'DOUBLE' THEN 1 ELSE 0 END) as totalDoubles,
                SUM(CASE WHEN ms.verdict = 'POINT' AND ms.scorer_id = :fencerId THEN 1 ELSE 0 END) as totalPoints,
                SUM(CASE WHEN ms.control IS TRUE AND ms.scorer_id = :fencerId THEN 1 ELSE 0 END) as totalControls,
                SUM(CASE WHEN ms.afterblow IS TRUE AND ms.scorer_id = :fencerId THEN 1 ELSE 0 END) as totalAfterblows
            FROM match_scores ms
            JOIN matches m ON ms.match_id = m.id
            WHERE 
                (m.fencer_1 = :fencerId OR m.fencer_2 = :fencerId)
        """
    }

}