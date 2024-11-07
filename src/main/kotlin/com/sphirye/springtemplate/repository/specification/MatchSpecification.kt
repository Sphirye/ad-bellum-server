package com.sphirye.springtemplate.repository.specification

import com.sphirye.springtemplate.model.Match
import com.sphirye.springtemplate.model.Match_
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

object MatchSpecification {

    fun query(example: Match): Specification<Match> {
        return Specification<Match> { root, _, criteriaBuilder ->
            criteriaBuilder.and(
                // Fencers
                _equalsFencerId(example.fencer_1_id, root, criteriaBuilder),
                _equalsFencerId(example.fencer_2_id, root, criteriaBuilder),
            )
        }
    }

    private fun _equalsFencerId(fencerId: Long?, root: Root<Match>, criteriaBuilder: CriteriaBuilder): Predicate {
        return if (fencerId != null) {
            criteriaBuilder.or(
                criteriaBuilder.equal(root.get(Match_.fencer_1_id), fencerId),
                criteriaBuilder.equal(root.get(Match_.fencer_2_id), fencerId),
            )
        } else {
            criteriaBuilder.conjunction()
        }
    }

}
