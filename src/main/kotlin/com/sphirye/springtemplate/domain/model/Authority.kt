package com.sphirye.springtemplate.domain.model

data class Authority(
    val id: Long? = null,
    val role: Role,
    val description: String? = null,
    val enabled: Boolean? = true,
) {
    enum class Role {
        SUPER_ADMIN, ADMIN, MOD
    }
}
