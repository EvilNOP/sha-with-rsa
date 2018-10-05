package io.github.evilnop.utils

import io.github.evilnop.constant.KeyPairGeneratorAlgorithm
import org.springframework.stereotype.Component
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

@Component
class RSAUtils {

    private val privateKey: RSAPrivateKey
    private val publicKey: RSAPublicKey

    init {
        val keyPairGenerator = KeyPairGenerator.getInstance(KeyPairGeneratorAlgorithm.RSA.rawValue)
        val keyPair = keyPairGenerator.genKeyPair()

        privateKey = keyPair.private as RSAPrivateKey
        publicKey = keyPair.public as RSAPublicKey

        println("Generated private key exponent: ${privateKey.privateExponent}")
        println("Generated private key modulus: ${privateKey.modulus}")

        println("Generated private key exponent: ${publicKey.publicExponent}")
        println("Generated private key modulus: ${publicKey.modulus}")
    }

    private fun loadKeyBytes(keyPath: String): ByteArray {
        val keyFile = File(keyPath)
        val fileInputStream = FileInputStream(keyFile)
        val keyBytes = ByteArray(keyFile.length().toInt())

        DataInputStream(fileInputStream).use { dataInputStream ->
            dataInputStream.readFully(keyBytes)
        }

        return keyBytes
    }

    fun loadRSAPrivateKey(privateKeyPath: String? = null): PrivateKey =
        privateKeyPath?.let {
            val rsaPrivateKeyBytes = loadKeyBytes(privateKeyPath)
            val pkcs8EncodedKeySpec = PKCS8EncodedKeySpec(rsaPrivateKeyBytes)
            val keyFactory = KeyFactory.getInstance(KeyPairGeneratorAlgorithm.RSA.rawValue)
            val privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec)
            val rsaPrivateKey = privateKey as RSAPrivateKey

            println("Private exponent: $rsaPrivateKey.privateExponent")
            println("Private modulus: ${rsaPrivateKey.modulus}")

            privateKey
        } ?: privateKey

    fun loadRSAPublicKey(publicKeyPah: String? = null): PublicKey =
        publicKeyPah?.let {
            val rsaPublicKeyBytes = loadKeyBytes(publicKeyPah)
            val x509EncodedKeySpec = X509EncodedKeySpec(rsaPublicKeyBytes)
            val keyFactory = KeyFactory.getInstance(KeyPairGeneratorAlgorithm.RSA.rawValue)
            val rsaPublicKey = keyFactory.generatePublic(x509EncodedKeySpec) as RSAPublicKey

            println("Public exponent: ${rsaPublicKey.publicExponent}")
            println("Public modulus: ${rsaPublicKey.modulus}")

            rsaPublicKey
        } ?: publicKey
}
