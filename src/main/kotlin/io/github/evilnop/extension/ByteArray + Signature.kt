package io.github.evilnop.extension

import io.github.evilnop.constant.SignatureAlgorithm
import java.security.PrivateKey
import java.security.Signature
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.RSAPrivateKey

fun ByteArray.signed(signatureAlgorithm: SignatureAlgorithm, privateKey: PrivateKey): ByteArray =
    Signature.getInstance(signatureAlgorithm.rawValue).apply {
        initSign(privateKey)
        update(this@signed)
    }.sign()

fun ByteArray.noneWithRSASigned(rsaPrivateKey: RSAPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.NONE_WITH_RSA, rsaPrivateKey)

fun ByteArray.md2WithRSASigned(rsaPrivateKey: RSAPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.MD2_WITH_RSA, rsaPrivateKey)

fun ByteArray.md5WithRSASigned(rsaPrivateKey: RSAPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.MD5_WITH_RSA, rsaPrivateKey)

fun ByteArray.sha1WithRSASigned(rsaPrivateKey: RSAPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.SHA1_WITH_RSA, rsaPrivateKey)

fun ByteArray.sha224WithRSASigned(rsaPrivateKey: RSAPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.SHA224_WITH_RSA, rsaPrivateKey)

fun ByteArray.sha256WithRSASigned(rsaPrivateKey: RSAPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.SHA256_WITH_RSA, rsaPrivateKey)

fun ByteArray.sha384WithRSASigned(rsaPrivateKey: RSAPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.SHA384_WITH_RSA, rsaPrivateKey)

fun ByteArray.sha512WithRSASigned(rsaPrivateKey: RSAPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.SHA512_WITH_RSA, rsaPrivateKey)

fun ByteArray.md5AndSHA1WithRSASigned(rsaPrivateKey: RSAPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.MD5_AND_SHA1_WITH_RSA, rsaPrivateKey)

fun ByteArray.noneWithECDSASigned(ecPrivateKey: ECPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.NONE_WITH_ECDSA, ecPrivateKey)

fun ByteArray.sha1withECDSASigned(ecPrivateKey: ECPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.SHA1_WITH_ECDSA, ecPrivateKey)

fun ByteArray.sha224withECDSASigned(ecPrivateKey: ECPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.SHA224_WITH_ECDSA, ecPrivateKey)

fun ByteArray.sha256withECDSASigned(ecPrivateKey: ECPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.SHA256_WITH_ECDSA, ecPrivateKey)

fun ByteArray.sha384withECDSASigned(ecPrivateKey: ECPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.SHA384_WITH_ECDSA, ecPrivateKey)

fun ByteArray.sha512withECDSASigned(ecPrivateKey: ECPrivateKey): ByteArray =
    this.signed(SignatureAlgorithm.SHA512_WITH_ECDSA, ecPrivateKey)
