# Itemist Evolved

## Compatibility

This application targets Android (6.0) Marshmallow and newer.

## Project Modules

This particular Android project consists of the following modules:

1. **utility** - various utils such as logger, view or animations util classes,
2. **persistence** - implements persistence models, converters and managers,
3. **network** - implements network models, converters and managers,
4. **domain** - merges persistence and network layer into one, incl. domain models and managers,
5. **application** - produces ItemistEvolved executable application.

## Project Flavors

Build is configured to produce two different flavors:

  - `dev` - flavor with enabled all development tools,
  - `prod` - flavor with disabled all development tools.

## Documentation and configuration

Do not hesitate to consult `settings` directory. You can find there such directories as:

  - `api` - with readme that contains description of client <-> server communication,
  - `keystore` - with signing configuration details,
  - `proguard` - with ProGuard definitions,
  - `services` - with readme that contains description of any external services integration.

## Building production

Before building production ensure to:

  - change build flavor to `prod`.

This flavor uses production configuration files. These files introduces crucial changes:

  - defines proper server connection details (uris, etc.),
  - enables/disables development tools such as Stetho, etc.,
  - configures network requests log level.

## Github Actions

How to store files in secrets:

  - Encrypt: gpg -c --armor file
  - Decrypt: use this github action - https://github.com/marketplace/actions/secret-into-file-action
  - Manual decrypt: gpg -d --passphrase [Password] --batch file.asc > resultfile

## Gradle properties

There are available options that can be configured in `utility\gradle.properties` file.

> Note that you can find `gradle.properties.sample` file in application modules. If you would like to use default values, just copy this file and paste as `gradle.properties`.

After preparing `gradle.properties` file you have to synchronize your Gradle build. This allows to generate `BuildConfig` class with required constant variables.
