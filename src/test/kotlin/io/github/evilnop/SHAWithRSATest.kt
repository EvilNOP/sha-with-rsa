package io.github.evilnop

import io.github.evilnop.constant.KeyPairGeneratorAlgorithm
import io.github.evilnop.extension.noneWithRSASigned
import io.github.evilnop.extension.sha256WithRSASigned
import io.github.evilnop.extension.toHexString
import io.github.evilnop.extension.toSHA256Digest
import org.bouncycastle.asn1.DERNull
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers
import org.bouncycastle.asn1.x509.AlgorithmIdentifier
import org.bouncycastle.asn1.x509.DigestInfo
import org.junit.Test
import java.security.KeyPairGenerator
import java.security.Security
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import kotlin.test.BeforeTest

class SHAWithRSATest {

    private lateinit var privateKey: RSAPrivateKey
    private lateinit var publicKey: RSAPublicKey

    @BeforeTest
    fun setUp() {
        val keyPairGenerator = KeyPairGenerator.getInstance(KeyPairGeneratorAlgorithm.RSA.rawValue)
        val keyPair = keyPairGenerator.genKeyPair()

        privateKey = keyPair.private as RSAPrivateKey
        publicKey = keyPair.public as RSAPublicKey

        println("Generated private key exponent: ${privateKey.privateExponent}")
        println("Generated private key modulus: ${privateKey.modulus}")

        println("Generated private key exponent: ${publicKey.publicExponent}")
        println("Generated private key modulus: ${publicKey.modulus}")

        val keyPairGenerators = mutableListOf<String>()
        val signatures = mutableListOf<String>()
        val messageDigests = mutableListOf<String>()
        val ciphers = mutableListOf<String>()

        Security.getProviders().forEach {
            it.services.forEach { service ->
                when (service.type) {
                    "KeyPairGenerator" -> keyPairGenerators.add(service.algorithm)
                    "Signature" -> signatures.add(service.algorithm)
                    "MessageDigest" -> messageDigests.add(service.algorithm)
                    "Cipher" -> ciphers.add(service.algorithm)
                }
            }
        }

        println("KeyPairGenerator: ")
        println(keyPairGenerators)

        println("Signatures: ")
        println(signatures)

        println("MessageDigests: ")
        println(messageDigests)

        println("Ciphers: ")
        println(ciphers)
    }

    @Test
    fun shaWithRSA() {
        val messageData = "Hello World".toByteArray()
        val messageDigest = messageData.toSHA256Digest()

        val sha256AID = AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE)
        val digestInfo = DigestInfo(sha256AID, messageDigest)
        val encodedDigestInfo = digestInfo.toASN1Primitive().encoded

        // Sign SHA256 with RSA
        val rsaSigned = encodedDigestInfo.noneWithRSASigned(privateKey)

        println("None with RSA Sign : ${rsaSigned.toHexString()}")
        println("Message Digest : ${messageDigest.toHexString()}")
        println("SHA 256 Aid :  + ${sha256AID.algorithm}")
        println("Encoded Degist Info :  + ${encodedDigestInfo.toHexString()}")

        // Sign SHA256withRSA as a single step
        val sha256WithRSASigned = messageData.sha256WithRSASigned(privateKey)

        println("SHA256 with RSA Sign: " + sha256WithRSASigned.toHexString())

        assert(rsaSigned.contentEquals(sha256WithRSASigned))
    }
}
