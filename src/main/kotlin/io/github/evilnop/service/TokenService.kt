package io.github.evilnop.service

interface TokenService {

    fun allocate(userId: Int): String

    fun verify(token: String): Int
}
