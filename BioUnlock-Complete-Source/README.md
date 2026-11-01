# BioUnlock — Android Fingerprint Laptop Unlock

BioUnlock is a **source-code starter project** for phone-assisted Windows session unlock.

## Architecture

1. Android authenticates the user with `BiometricPrompt`.
2. A private signing key is generated in Android Keystore with biometric authentication required.
3. The phone signs a fresh challenge; fingerprint templates never leave Android.
4. A Windows Credential Provider is the supported integration point for the Windows sign-in/lock screen.

## Repository layout

- `android/` — Android Studio project (Kotlin, AndroidX Biometric, Keystore signing core).
- `windows/CredentialProvider/` — native C++ Credential Provider project scaffold and implementation notes.
- `protocol/` — challenge/signature protocol documentation.

## Important status

This is a **starter source project, not a finished or tested unlock product**. The Android biometric/key-signing core is implemented. The Windows Credential Provider is a native integration scaffold: it must be completed, built, signed, installed, and tested on a disposable Windows test machine before it can unlock a session. Do not install an untrusted Credential Provider DLL on your primary Windows account.

The original MuleRun task page did not expose downloadable source files in its shared-page text, so this repository is a clean source implementation based on the architecture and choices described in the task, not a recovery of MuleRun's hidden generated files.

## Requirements

- Android Studio with Android SDK 35 and JDK 17.
- Windows 10/11, Visual Studio 2022 with Desktop development with C++ and Windows SDK.
- A BLE-capable Windows device and Android phone for the transport implementation.
- For real Windows lock-screen integration, implement the documented Credential Provider interfaces and test in a VM.

## Build Android

Open `android/` in Android Studio and sync Gradle. The project uses Kotlin and AndroidX Biometric.

## Security notes

- No fingerprint image/template is read or transmitted.
- Private key material remains in Android Keystore.
- The Windows side must verify a signed, single-use, time-limited challenge against the enrolled public key.
- Add pairing, replay protection, rate limiting, key revocation, and secure enrollment before production use.
