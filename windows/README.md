# Windows integration

Windows lock-screen integration requires a native **Credential Provider COM DLL**. A normal tray app/service cannot unlock the Winlogon secure desktop.

This directory is a Visual Studio C++ scaffold only. It intentionally does not claim to be a working unlock provider. To complete it:

1. Implement `ICredentialProvider`, `ICredentialProviderCredential`, and the required serialization flow for the target Windows versions.
2. Implement BLE discovery/connection and authenticated challenge exchange.
3. Verify ECDSA P-256 signatures against an enrolled phone public key.
4. Enforce expiry, single-use nonce, user binding, revocation and rate limits.
5. Sign the DLL, register it only in a disposable VM, and test recovery/rollback.

Never deploy an unreviewed Credential Provider DLL on a production machine; a broken provider can lock users out.
