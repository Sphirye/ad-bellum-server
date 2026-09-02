package com.sphirye.springtemplate.application.service

import com.sphirye.shared.exception.exceptions.ConflictException
import com.sphirye.springtemplate.domain.model.Match
import com.sphirye.springtemplate.domain.model.MatchFilter
import com.sphirye.springtemplate.domain.model.ScoreProfile
import com.sphirye.springtemplate.domain.model.ScoreProfile.ScoreProfileType
import com.sphirye.springtemplate.domain.port.inbound.ScoreProfileUseCase
import com.sphirye.springtemplate.domain.port.outbound.MatchPersistencePort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class MatchServiceTest {

    @Test
    fun `finish should set state, resolution and winner fencer`() {
        val store = mutableMapOf(1L to Match(id = 1L, fencer_1_id = 1L, fencer_2_id = 2L, state = Match.MatchState.IN_PROGRESS))
        val service = MatchService(FakeMatchPersistencePort(store), FakeScoreProfileUseCase())

        val result = service.finish(
            1L,
            Match(
                state = Match.MatchState.FINISHED,
                resolution = Match.MatchResolution.POINTS_REACHED,
                winnerFencerId = 1L,
            ),
        )

        assertEquals(Match.MatchState.FINISHED, result.state)
        assertEquals(Match.MatchResolution.POINTS_REACHED, result.resolution)
        assertEquals(1L, result.winnerFencerId)
    }

    @Test
    fun `update of a finished match should throw ConflictException`() {
        val store = mutableMapOf(1L to Match(id = 1L, fencer_1_id = 1L, fencer_2_id = 2L, state = Match.MatchState.FINISHED))
        val service = MatchService(FakeMatchPersistencePort(store), FakeScoreProfileUseCase())

        assertThrows(ConflictException::class.java) {
            service.update(1L, Match(fencer_1_id = 1L, fencer_2_id = 2L, state = Match.MatchState.IN_PROGRESS))
        }
    }

    @Test
    fun `create should clone the score profile into an instance`() {
        val store = mutableMapOf<Long, Match>()
        val service = MatchService(FakeMatchPersistencePort(store), FakeScoreProfileUseCase())
        val template = ScoreProfile(name = "Template", type = ScoreProfileType.TEMPLATE)

        val created = service.create(
            Match(fencer_1_id = 1L, fencer_2_id = 2L, state = Match.MatchState.WAITING, scoreProfile = template),
        )

        assertEquals(99L, created.scoreProfileId)
        assertEquals(1L, created.id)
    }
}

private class FakeScoreProfileUseCase : ScoreProfileUseCase {
    override fun findById(id: Long): ScoreProfile = ScoreProfile(id = id)

    override fun findAll(example: ScoreProfile?, pageable: Pageable): Page<ScoreProfile> = Page.empty()

    override fun createProfileScore(profile: ScoreProfile): ScoreProfile = profile.copy(id = 1L)

    override fun instance(profile: ScoreProfile): ScoreProfile =
        profile.withoutIds().copy(id = 99L, type = ScoreProfileType.INSTANCE)

    override fun update(id: Long, profile: ScoreProfile): ScoreProfile = profile.copy(id = id)

    override fun updateTimeLeft(id: Long, timeLeft: Int): ScoreProfile = findById(id).copy(timeLeft = timeLeft)

    override fun updateElapsedTime(id: Long, elapsedTime: Int): ScoreProfile =
        findById(id).copy(elapsedTimeInSeconds = elapsedTime)

    override fun delete(id: Long) {}
}

private class FakeMatchPersistencePort(
    private val store: MutableMap<Long, Match>,
) : MatchPersistencePort {

    override fun findById(id: Long): Match? = store[id]

    override fun findAll(pageable: Pageable): Page<Match> = Page.empty()

    override fun findAllByFilter(filter: MatchFilter, pageable: Pageable): Page<Match> = Page.empty()

    override fun existsById(id: Long): Boolean = store.containsKey(id)

    override fun existsByIdAndState(id: Long, state: Match.MatchState): Boolean = store[id]?.state == state

    override fun save(match: Match): Match {
        val saved = if (match.id == null) match.copy(id = (store.size + 1L).coerceAtLeast(1L)) else match
        store[saved.id!!] = saved
        return saved
    }

    override fun deleteById(id: Long) {
        store.remove(id)
    }
}
