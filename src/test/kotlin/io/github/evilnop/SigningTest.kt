package io.github.evilnop

import io.github.evilnop.constant.KeyPairGeneratorAlgorithm
import io.github.evilnop.extension.md2WithRSASigned
import io.github.evilnop.extension.md5AndSHA1WithRSASigned
import io.github.evilnop.extension.md5WithRSASigned
import io.github.evilnop.extension.noneWithECDSASigned
import io.github.evilnop.extension.noneWithRSASigned
import io.github.evilnop.extension.sha1WithRSASigned
import io.github.evilnop.extension.sha1withECDSASigned
import io.github.evilnop.extension.sha224WithRSASigned
import io.github.evilnop.extension.sha224withECDSASigned
import io.github.evilnop.extension.sha256WithRSASigned
import io.github.evilnop.extension.sha256withECDSASigned
import io.github.evilnop.extension.sha384WithRSASigned
import io.github.evilnop.extension.sha384withECDSASigned
import io.github.evilnop.extension.sha512WithRSASigned
import io.github.evilnop.extension.sha512withECDSASigned
import java.security.KeyPairGenerator
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import kotlin.test.BeforeTest
import kotlin.test.Test

class SigningTest {

    private val messageData = "Hello World".toByteArray()

    private lateinit var ecPrivateKey: ECPrivateKey
    private lateinit var ecPublicKey: ECPublicKey

    private lateinit var rsaPrivateKey: RSAPrivateKey
    private lateinit var rsaPublicKey: RSAPublicKey

    @BeforeTest
    fun setUp() {
        val keyPairGenerator = KeyPairGenerator.getInstance(KeyPairGeneratorAlgorithm.EC.rawValue)
        val keyPair = keyPairGenerator.genKeyPair()

        ecPrivateKey = keyPair.private as ECPrivateKey
        ecPublicKey = keyPair.public as ECPublicKey

        val rsaKeyPairGenerator = KeyPairGenerator.getInstance(KeyPairGeneratorAlgorithm.RSA.rawValue)
        val rsaKeyPair = rsaKeyPairGenerator.genKeyPair()

        rsaPrivateKey = rsaKeyPair.private as RSAPrivateKey
        rsaPublicKey = rsaKeyPair.public as RSAPublicKey
    }

    @Test
    fun signWithRSA() {
        assert(messageData.noneWithRSASigned(rsaPrivateKey).any())
        assert(messageData.md2WithRSASigned(rsaPrivateKey).any())
        assert(messageData.md5WithRSASigned(rsaPrivateKey).any())
        assert(messageData.sha1WithRSASigned(rsaPrivateKey).any())
        assert(messageData.sha224WithRSASigned(rsaPrivateKey).any())
        assert(messageData.sha256WithRSASigned(rsaPrivateKey).any())
        assert(messageData.sha384WithRSASigned(rsaPrivateKey).any())
        assert(messageData.sha512WithRSASigned(rsaPrivateKey).any())
        assert(messageData.md5AndSHA1WithRSASigned(rsaPrivateKey).any())
    }

    @Test
    fun signWithECDSA() {
        assert(messageData.noneWithECDSASigned(ecPrivateKey).any())
        assert(messageData.sha1withECDSASigned(ecPrivateKey).any())
        assert(messageData.sha224withECDSASigned(ecPrivateKey).any())
        assert(messageData.sha256withECDSASigned(ecPrivateKey).any())
        assert(messageData.sha384withECDSASigned(ecPrivateKey).any())
        assert(messageData.sha512withECDSASigned(ecPrivateKey).any())
    }
}
