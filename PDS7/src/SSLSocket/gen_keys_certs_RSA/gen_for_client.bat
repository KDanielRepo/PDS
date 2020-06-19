@echo off

echo -------------------------------------------------------------
echo Generowanie magazynu kluczy
echo -------------------------------------------------------------
keytool -genkey -keystore clientstore -keyalg rsa -alias clientstore -storepass testpass -keypass testpass -dname "CN=Demo klienta SSL, O=KIK WE"

echo -------------------------------------------------------------
echo Generowanie certyfikatu (eksport)
echo -------------------------------------------------------------
keytool -export -keystore clientstore -storepass testpass -alias clientstore -file client.cer

echo -------------------------------------------------------------
echo Wydruk certyfikatu
echo -------------------------------------------------------------
keytool -printcert -file client.cer

echo -------------------------------------------------------------
echo Dodanie certyfikatu do zaufanych (trusted) w pliku cacerts
echo -------------------------------------------------------------
keytool -import  -storepass testpass -alias clientstore -file client.cer -keystore cacerts

echo -------------------------------------------------------------
echo Lista zaufanych certyfikatow w cacerts
echo -------------------------------------------------------------
keytool -list -keystore cacerts -storepass testpass

pause