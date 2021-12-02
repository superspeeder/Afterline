@echo off
rmdir /S /Q %~dp0\..\gen\
mkdir %~dp0\..\gen
protoc --proto_path=%~dp0 --java_out=%~dp0\..\gen\ %~dp0\afterline\*.proto