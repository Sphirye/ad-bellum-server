package com.sphirye.springtemplate.model

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class MatchExample (
    var fencer1Id: Long? = null,

    var fencer2Id: Long? = null,

    var state: Match.MatchState? = null,

    var dateRange: DateRange? = null,

    var createdBy: String? = null,

) {
    fun toSpecification(): Specification<Match> {
        return Specification { root, query, cb ->
            val predicates = mutableListOf<Predicate>()

            fencer1Id?.let { _equalsFencerId(it, root, cb) }

            fencer2Id?.let {_equalsFencerId(it, root, cb) }

            state?.let { predicates.add(cb.equal(root.get<Match.MatchState>(Match_.STATE), it)) }

            dateRange?.let { range ->
                range.from?.let {
                    predicates.add(cb.greaterThanOrEqualTo(root.get(Match_.CREATED_DATE), it.toLocalDateTime()))
                }
                range.until?.let {
                    predicates.add(cb.lessThanOrEqualTo(root.get(Match_.CREATED_DATE), it.toLocalDateTime()))
                }
            }

            // TODO: Filter by createdBy

            cb.and(*predicates.toTypedArray())
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