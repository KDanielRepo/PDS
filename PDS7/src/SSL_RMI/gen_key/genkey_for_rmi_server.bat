@echo off

echo -------------------------------------------------------------
echo Generowanie magazynu kluczy
echo -------------------------------------------------------------
keytool -genkey -keystore serverstore -keyalg rsa -alias serverstore -storepass testpass -keypass testpass -dname "CN=Test serwera RMI, O=KIK Wydzial Elektroniki"

echo -------------------------------------------------------------
echo Generowanie certyfikatu (eksport)
echo -------------------------------------------------------------
keytool -export -keystore serverstore -storepass testpass -alias serverstore -file server.cer

echo -------------------------------------------------------------
echo Wydruk certyfikatu
echo -------------------------------------------------------------
keytool -printcert -file server.cer

echo -------------------------------------------------------------
echo Dodanie certyfikatu do zaufanych (trusted) w pliku cacerts
echo -------------------------------------------------------------
keytool -import  -storepass testpass -alias serverstore -file server.cer -keystore cacerts

echo -------------------------------------------------------------
echo Lista zaufanych certyfikatow w cacerts
echo -------------------------------------------------------------
keytool -list -keystore cacerts -storepass testpass

pause