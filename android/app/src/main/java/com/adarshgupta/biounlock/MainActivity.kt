package com.adarshgupta.biounlock

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.security.Signature

class MainActivity : AppCompatActivity() {
    private lateinit var status: TextView
    private lateinit var crypto: BiometricKeyManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crypto = BiometricKeyManager()
        status = TextView(this).apply {
            textSize = 16f
            text = "BioUnlock is ready. Enroll a key before signing a laptop challenge."
            setPadding(0, 24, 0, 24)
        }
        val enroll = Button(this).apply {
            text = "Create biometric key"
            setOnClickListener { createKey() }
        }
        val sign = Button(this).apply {
            text = "Test fingerprint authentication"
            setOnClickListener { authenticateTest() }
        }
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 48, 32, 32)
            addView(status)
            addView(enroll)
            addView(sign)
        }
        setContentView(layout)
    }

    private fun createKey() {
        val capability = BiometricManager.from(this)
            .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)
        if (capability != BiometricManager.BIOMETRIC_SUCCESS) {
            status.text = "Strong biometrics are unavailable or not enrolled on this device."
            return
        }
        try {
            crypto.createSigningKey()
            status.text = "Biometric signing key created in Android Keystore."
        } catch (e: Exception) {
            status.text = "Key creation failed: ${e.localizedMessage}"
        }
    }

    private fun authenticateTest() {
        try {
            val signature = crypto.newSignatureForChallenge(
                "BIOUNLOCK|1|LOCAL_TEST_CHALLENGE|1893456000|windows".toByteArray()
            )
            val prompt = BiometricPrompt(
                this,
                ContextCompat.getMainExecutor(this),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        val signed = result.cryptoObject?.signature
                        try {
                            signed?.update("BIOUNLOCK|1|LOCAL_TEST_CHALLENGE|1893456000|windows".toByteArray())
                            val bytes = signed?.sign()
                            status.text = if (bytes != null) {
                                "Fingerprint accepted. Test signature created (${bytes.size} bytes). No fingerprint data was accessed."
                            } else "Authentication succeeded, but no signature was returned."
                        } catch (e: Exception) {
                            status.text = "Signing failed: ${e.localizedMessage}"
                        }
                    }

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        status.text = "Authentication error: $errString"
                    }

                    override fun onAuthenticationFailed() {
                        status.text = "Fingerprint not recognized. Try again."
                    }
                }
            )
            prompt.authenticate(
                BiometricPrompt.PromptInfo.Builder()
                    .setTitle("BioUnlock authentication")
                    .setSubtitle("Authenticate to sign a laptop unlock challenge")
                    .setNegativeButtonText("Cancel")
                    .build(),
                BiometricPrompt.CryptoObject(signature)
            )
        } catch (e: Exception) {
            status.text = "Create the biometric key first. ${e.localizedMessage ?: ""}"
        }
    }
}
