keytool -genkeypair -keystore severkeystore -alias sever

keytool -certreq -keystore severkeystore -alias sever -file sever.csr

openssl x509 -req -days 365 -in sever.csr -CA Ca/cert.crt -CAkey Ca/ca.key -CAcreateserial -out signedcrt.crt

keytool -importcert -file Ca/cert.crt -keystore severkeystore

keytool -importcert -alias sever -file signedcrt.crt  -keystore severkeystore

keytool -list -v -keystore severkeystore

keytool -import -alias servertruststore -file Ca/cert.crt -keystore servertruststore -storepass password -storetype JKS
