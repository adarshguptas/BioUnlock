// BioUnlock Windows Credential Provider integration scaffold.
// This is NOT a complete ICredentialProvider implementation and must not be registered.
#include <windows.h>
#include <credentialprovider.h>
#include <bcrypt.h>
#include <string>

#pragma comment(lib, "bcrypt.lib")

// Security-critical implementation TODO:
// - Implement ICredentialProvider and ICredentialProviderCredential COM classes.
// - Display a tile and initiate an explicit phone challenge.
// - Receive the signed response over an authenticated BLE channel.
// - Verify ECDSA P-256 signature with BCrypt against enrolled public key.
// - Check challenge freshness, one-time use, relying-party binding and user binding.
// - Return only the correct Windows serialization after successful verification.
//
// A placeholder DLL is deliberately not registered and cannot unlock Windows.

BOOL APIENTRY DllMain(HMODULE, DWORD, LPVOID) {
    return TRUE;
}
