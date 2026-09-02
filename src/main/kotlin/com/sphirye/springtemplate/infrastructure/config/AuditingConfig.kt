package com.sphirye.springtemplate.infrastructure.config

import com.sphirye.springtemplate.domain.port.outbound.CurrentSessionPort
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.Optional

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditingConfig")
class AuditingConfig(
    private val _currentSessionPort: CurrentSessionPort,
) : AuditorAware<Long> {

    override fun getCurrentAuditor(): Optional<Long> {
        if (_currentSessionPort.isAuthenticated()) {
            val identity = _currentSessionPort.getUserIdentity()
            return Optional.of(identity.id)
        } else {
            return Optional.empty()
        }
    }

}
