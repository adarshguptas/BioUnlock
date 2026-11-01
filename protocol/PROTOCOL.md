# BioUnlock challenge/signature protocol (v1)

The phone must never send fingerprint data. The phone proves that a biometric-gated private key was used.

## Enrollment

1. Windows generates a random enrollment nonce.
2. Android creates an EC P-256 signing key in Android Keystore with `setUserAuthenticationRequired(true)` and a biometric-enrollment invalidation policy.
3. Android exports only the public key (X.509 SubjectPublicKeyInfo).
4. Windows associates that public key with the paired phone/user after an explicit trusted enrollment ceremony.

## Unlock attempt

1. Windows generates a cryptographically random 32-byte challenge and a short expiry (recommended 30 seconds).
2. Windows sends `{version, challenge, expiry, relyingParty}` over an authenticated paired channel.
3. Android displays the unlock request and invokes `BiometricPrompt` with a `CryptoObject` for the enrolled private key.
4. After successful biometric authentication, Android signs the exact canonical byte sequence:
   `BIOUNLOCK|1|<challenge-base64url>|<expiry-unix-seconds>|<relyingParty>`
5. Android returns `{version, signature, publicKeyId}`.
6. Windows verifies the signature using the enrolled public key, checks expiry, checks that the challenge is outstanding and unused, and consumes it atomically.

## Required protections

- BLE pairing/bonding plus application-level authenticated encryption.
- One-time challenges, expiry, replay cache, rate limits, and explicit phone/user enrollment.
- Do not accept a signature without checking the exact challenge and relying party.
- Do not unlock Windows merely because a Bluetooth device is nearby.
