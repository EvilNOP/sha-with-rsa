package io.github.evilnop.extension

import io.github.evilnop.constant.MessageDigestAlgorithm
import java.security.MessageDigest

fun ByteArray.digest(messageDigestAlgorithm: MessageDigestAlgorithm): ByteArray =
    MessageDigest.getInstance(messageDigestAlgorithm.rawValue).apply {
        update(this@digest)
    }.digest()

fun ByteArray.toSHA256Digest(): ByteArray = digest(MessageDigestAlgorithm.SHA256)
