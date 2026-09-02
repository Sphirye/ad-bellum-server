package com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.mapper

import com.sphirye.springtemplate.domain.model.MatchFilter
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Match as MatchEntity
import com.sphirye.springtemplate.infrastructure.adapters.outbound.persistence.entity.Match.MatchState as MatchEntityState
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

fun MatchFilter.toSpecification(): Specification<MatchEntity> {
    return Specification { root, _, cb ->
        val predicates = mutableListOf<Predicate>()

        fencer_1_id?.let { predicates.add(_equalsFencerId(it, root, cb)) }
        fencer_2_id?.let { predicates.add(_equalsFencerId(it, root, cb)) }

        groupId?.let { predicates.add(cb.equal(root.get<Any>("groupId"), it)) }

        state?.let {
            predicates.add(cb.equal(root.get<MatchEntityState>("state"), MatchEntityState.valueOf(it.name)))
        }

        dateRange?.let { range ->
            range.from?.let {
                predicates.add(cb.greaterThanOrEqualTo(root.get<java.time.LocalDateTime>("createdDate"), it.toLocalDateTime()))
            }
            range.until?.let {
                predicates.add(cb.lessThanOrEqualTo(root.get<java.time.LocalDateTime>("createdDate"), it.toLocalDateTime()))
            }
        }

        // TODO: Filter by createdBy

        cb.and(*predicates.toTypedArray())
    }
}

private fun _equalsFencerId(
    fencerId: Long?,
    root: Root<MatchEntity>,
    criteriaBuilder: CriteriaBuilder,
): Predicate {
    return if (fencerId != null) {
        criteriaBuilder.or(
            criteriaBuilder.equal(root.get<Any>("fencer_1_id"), fencerId),
            criteriaBuilder.equal(root.get<Any>("fencer_2_id"), fencerId),
        )
    } else {
        criteriaBuilder.conjunction()
    }
}
