package com.sphirye.springtemplate.infrastructure.adapters.outbound.security

import com.sphirye.springtemplate.domain.model.Authority
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

fun MutableSet<Authority>.toSimpleGrantedAuthorities(): List<SimpleGrantedAuthority> {
    return map { it.role.toString() }.map { SimpleGrantedAuthority(it) }
}

fun MutableSet<Authority>.toGrantedAuthorities(): MutableCollection<out GrantedAuthority> {
    return toSimpleGrantedAuthorities().toMutableList()
}
