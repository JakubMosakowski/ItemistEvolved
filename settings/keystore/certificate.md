# Application Signing

You can generate password by executing the following command:

```bash
date | md5sum
```

## Certificate generation

To generate a `debug.jks` certificate file:

```bash
keytool -genkey -v -keyalg RSA -keysize 2048 -validity 10000 \                    
  -dname 'CN=Android Debug,O=Android,C=US' \
  -keystore debug.jks -alias androiddebugkey \
  -storepass android
```

## List certificates

To list certificates stored in keystore:

```bash
keytool -v -list -keystore release.jks
```

To print details for certificate with given alias:

```bash
keytool -v -list -keystore release.jks -alias "ALIAS"
```

## Additional commands

Do not hesitate to check any additional `keytool` options:

> https://www.sslshopper.com/article-most-common-java-keytool-keystore-commands.html
