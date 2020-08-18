# Application Signing

## Alias 

> ItemistEvolved

## Passwords

| Type        | Keytool   | Password                         |
| :---------- | :-------- | :------------------------------- |
| keystore    | storepass | <todo> |

You can generate password by executing the following command:

```bash
date | md5sum
```

## Details

| Key                 | Shortcut | Value                      | 
| :------------------ | :------- | :------------------------- |
| First and Last Name | CN       | <todo>                     |
| Organization Unit   | OU       | <todo>                     |
| Organization        | O        | <todo>                     |
| Locality            | L        | <todo>                     |
| State               | ST       | <todo>                     |
| Country Code        | C        | <todo>                     |

## Certificate generation

To generate a `debug.jks` certificate file:

```bash
keytool -genkey -v -keyalg RSA -keysize 2048 -validity 10000 \                    
  -dname 'CN=Android Debug,O=Android,C=US' \
  -keystore debug.jks -alias androiddebugkey \
  -storepass android
```

To generate a `release.jks` certificate file:

```bash
keytool -genkey -v -keyalg RSA -keysize 2048 -validity 1460 \
  -dname 'CN=<todo>, OU=<todo>, O=<todo>, L=<todo>, ST=<todo>, C=<todo>' \
  -keystore release.jks -alias ItemistEvolved \
  -storepass <todo>
```

## List certificates

To list certificates stored in keystore:

```bash
keytool -v -list -keystore release.jks
```

To print details for certificate with given alias:

```bash
keytool -v -list -keystore release.jks -alias ItemistEvolved
```

## Additional commands

Do not hesitate to check any additional `keytool` options:

> https://www.sslshopper.com/article-most-common-java-keytool-keystore-commands.html
