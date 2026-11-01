cmake_minimum_required(VERSION 3.24)
project(BioUnlockCredentialProvider LANGUAGES CXX)
set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
add_library(BioUnlockCredentialProvider SHARED
    BioUnlockCredentialProvider.cpp
)
target_compile_definitions(BioUnlockCredentialProvider PRIVATE UNICODE _UNICODE)
target_link_libraries(BioUnlockCredentialProvider PRIVATE ole32 oleaut32 bcrypt)
