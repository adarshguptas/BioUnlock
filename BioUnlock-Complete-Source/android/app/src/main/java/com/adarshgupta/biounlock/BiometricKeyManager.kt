package com.adarshgupta.biounlock

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyPairGenerator
import java.security.Signature
import java.security.KeyStore
import java.security.spec.ECGenParameterSpec

class BiometricKeyManager {
    companion object {
        private const val ALIAS = "biounlock_signing_key_v1"
    }

    fun createSigningKey() {
        val generator = KeyPairGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_EC, "AndroidKeyStore"
        )
        val spec = KeyGenParameterSpec.Builder(
            ALIAS,
            KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY
        )
            .setAlgorithmParameterSpec(ECGenParameterSpec("secp256r1"))
            .setDigests(KeyProperties.DIGEST_SHA256)
            .setUserAuthenticationRequired(true)
            .setInvalidatedByBiometricEnrollment(true)
            .build()
        generator.initialize(spec)
        generator.generateKeyPair()
    }

    fun newSignatureForChallenge(challenge: ByteArray): Signature {
        require(challenge.isNotEmpty()) { "Challenge must not be empty" }
        val store = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
        val privateKey = store.getKey(ALIAS, null) as? java.security.PrivateKey
            ?: error("Biometric key not found. Create it first.")
        return Signature.getInstance("SHA256withECDSA").apply {
            initSign(privateKey)
        }
    }

    fun exportPublicKeyDer(): ByteArray {
        val store = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
        val certificate = store.getCertificate(ALIAS)
            ?: error("Biometric key not found.")
        return certificate.publicKey.encoded
    }
}
