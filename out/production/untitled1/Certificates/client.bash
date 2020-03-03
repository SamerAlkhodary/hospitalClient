declare  -a arguments=("cn=Jamil Maqdis,o=D1,ou=Doctor,c=Lund"  "cn=Momo Abo,o=D2,ou=Doctor,c=Lund" "cn=Aslan Murjan,o=D1,ou=Patient,c=Lund"
                         "cn=Anas Mofleh,o=D2,ou=Patient,c=Lund" "cn=Samer Alkhodary,o=D2,ou=Nurse,c=Lund" "cn=Lara Croft,o=D1,ou=Nurse,c=Lund" "cn=Health Department,o=D0,ou=Government,c=Sweden")
i=0
for s  in "${arguments[@]}"; do
keytool -genkeypair -keystore clientkeystore -dname $s -alias client -keypass password -storepass password -keyalg RSA
((i=i+1))

keytool -certreq -keystore clientkeystore -alias client -file client.csr -storepass password

openssl x509 -req -days 365 -in client.csr -CA Ca/cert.crt -CAkey Ca/ca.key -CAcreateserial -out signedcrt.crt

keytool -importcert -file Ca/cert.crt -keystore clientkeystore -storepass password

keytool -importcert -alias client -file signedcrt.crt  -keystore clientkeystore -storepass password
mkdir $i
mv  client.csr $i/
mv  clientkeystore $i/
mv  signedcrt.crt $i/



done
