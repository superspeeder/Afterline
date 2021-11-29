@echo off
protoc --proto_path=%~dp0 --java_out=%~dp0\..\gen\ %~dp0\afterline\*.proto