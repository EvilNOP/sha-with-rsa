package io.github.evilnop.extension

fun ByteArray.toHexString(): String =
    StringBuffer().apply {
        this@toHexString.forEach {
            val hex = Integer.toHexString(0xFF and it.toInt())

            if (hex.length == 1) {
                append('0')
            }

            append(hex)
        }
    }.toString()
