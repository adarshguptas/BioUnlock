# Build scaffold

Open a Visual Studio 2022 Developer PowerShell, then:

```powershell
cmake -S . -B build -A x64
cmake --build build --config Release
```

This only builds the scaffold DLL. It does **not** implement the Credential Provider COM interfaces and does not unlock Windows. Do not register or deploy it.
