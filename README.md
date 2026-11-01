 <div align="center">

# 🔐 BioUnlock

### Android Biometric Authentication for Windows

[![Platform](https://img.shields.io/badge/Platform-Android%20%7C%20Windows-blue?style=for-the-badge)](https://github.com/adarshguptas/BioUnlock)
[![Language](https://img.shields.io/badge/Language-Kotlin%20%7C%20C%2B%2B-orange?style=for-the-badge)](https://github.com/adarshguptas/BioUnlock)
[![Status](https://img.shields.io/badge/Status-Prototype-yellow?style=for-the-badge)](https://github.com/adarshguptas/BioUnlock)

**Bringing Android biometric authentication to Windows login workflows.**

[Explore Project](https://github.com/adarshguptas/BioUnlock) · [Report Bug](https://github.com/adarshguptas/BioUnlock/issues) · [Request Feature](https://github.com/adarshguptas/BioUnlock/issues)

</div>

---

## 🚀 About The Project

**BioUnlock** is an Android and Windows integration prototype that explores using smartphone biometric authentication as an additional method for Windows session unlocking.

The project is based on Android BiometricPrompt, Android Keystore and a proposed cryptographic challenge-response architecture.

## ✨ Features

- 🔐 Android biometric authentication
- 🛡️ Secure key storage using Android Keystore
- 🔑 Cryptographic challenge-signing concept
- 📱 Android application starter
- 💻 Windows Credential Provider scaffold
- 📡 Bluetooth communication planned
- 📖 Authentication protocol documentation

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Android development |
| Android SDK | Mobile application |
| AndroidX Biometric | Biometric authentication |
| Android Keystore | Cryptographic key protection |
| C++ | Windows integration scaffold |
| CMake | Native build configuration |
| Gradle | Android build system |

## 📂 Project Structure

```text
BioUnlock/
│
├── android/
│   └── app/
│       └── src/
│           └── main/
│
├── windows/
│   └── CredentialProvider/
│
├── protocol/
│   └── PROTOCOL.md
│
├── BioUnlock-1.0-release.apk
├── .gitignore
└── README.md
```

## ⚙️ Getting Started

### Prerequisites

- Android Studio
- Android SDK
- JDK 17
- Windows 10/11 for future Windows integration

### 1. Clone Repository

```bash
git clone https://github.com/adarshguptas/BioUnlock.git
```

### 2. Navigate to Project

```bash
cd BioUnlock
```

### 3. Open Android Project

Open the `android` folder in Android Studio.

### 4. Build Application

Allow Gradle synchronization and build the Android application using Android Studio.

## 📥 Download APK

The repository includes an Android release APK.

[Download BioUnlock APK](https://github.com/adarshguptas/BioUnlock/raw/main/BioUnlock-1.0-release.apk)

## 🔒 Security Architecture

1. Android requests biometric authentication through BiometricPrompt.
2. Android Keystore protects the private signing key.
3. The proposed protocol uses a fresh cryptographic challenge.
4. Windows is intended to verify the signed response.

**Privacy:** Fingerprint templates are not intended to leave the Android device.

## 🗺️ Development Roadmap

- [x] Android project starter
- [x] Biometric authentication core
- [x] Android Keystore integration
- [x] Protocol documentation
- [x] Windows Credential Provider scaffold
- [ ] Bluetooth communication
- [ ] Complete Windows Credential Provider
- [ ] Device pairing and enrollment
- [ ] End-to-end testing
- [ ] Production-ready release

## ⚠️ Project Status

BioUnlock is currently a **prototype/starter project**, not a fully functional Windows unlocking product.

Bluetooth communication and complete Windows Credential Provider integration are still under development. The Windows scaffold is not ready for installation on a production machine.

## 🤝 Contributing

Contributions are welcome!

1. Fork this repository.
2. Create a new branch.
3. Make your changes.
4. Commit your changes.
5. Submit a Pull Request.

## 👨‍💻 Author

**Adarsh Gupta**

- GitHub: [@adarshguptas](https://github.com/adarshguptas)

---

<div align="center">

### ⭐ Support The Project

If you like BioUnlock, consider giving this repository a star!

**Made with ❤️ by Adarsh Gupta**

</div>
