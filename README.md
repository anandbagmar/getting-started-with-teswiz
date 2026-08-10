# getting-started-with-teswiz

### This is a sample project to understand and start using [teswiz](https://github.com/anandbagmar/teswiz)

## teswiz
[![](https://badges.frapsoft.com/os/v3/open-source.svg)](https://github.com/anandbagmar/teswiz)
[![GitHub stars](https://img.shields.io/github/stars/anandbagmar/teswiz.svg?style=flat)](https://github.com/anandbagmar/teswiz/stargazers)
[ ![PRs Welcome](https://img.shields.io/badge/PRs-Welcome-brightgreen.svg?style=flat )](https://github.com/anandbagmar/teswiz/pulls)
[![GitHub forks](https://img.shields.io/github/forks/anandbagmar/teswiz.svg?style=social&label=Fork)](https://github.com/anandbagmar/teswiz/network)

## Latest teswiz release status:
[![0.0.86](https://jitpack.io/v/anandbagmar/teswiz.svg)](https://jitpack.io/#anandbagmar/teswiz)
[![CI](https://github.com/anandbagmar/teswiz/actions/workflows/Build_And_Run_Unit_Tests_CI.yml/badge.svg)](https://github.com/anandbagmar/teswiz/actions/workflows/Build_And_Run_Unit_Tests_CI.yml)
[![CodeQL](https://github.com/anandbagmar/teswiz/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/anandbagmar/teswiz/actions/workflows/codeql-analysis.yml)

## Latest successful teswiz build id:
[![Latest Commit](https://img.shields.io/badge/commit-f8c27a60b3-blue.svg)](https://jitpack.io/#anandbagmar/teswiz)


## 🚨 Breaking Changes

### From Version `1.0.13` onward

As part of package restructuring, context-related classes have moved to a new package.

#### ❗ Required Update in Imports

Replace:

```java
import com.context.SessionContext;
import com.context.TestExecutionContext;
```

With:

```java
import com.znsio.teswiz.context.SessionContext;
import com.znsio.teswiz.context.TestExecutionContext;
```

# NOTE

    Use JDK v17 or higher

## Step to start using tewiz in your project:

* Clone/download the getting-started-with-teswiz repo
* For the android apk, find the package and activity
  aapt dump badging src/test/resources/sampleApps/<apkname>.apk | grep package
  aapt dump badging src/test/resources/sampleApps/<apkname>.apk | grep activity
* Create a copy of an existing configs file - ex: ./configs/myapp.properties
  Update `APP_PACKAGE_NAME` and `APP_NAME`, `BASE_URL`
* Create a copy of an existing capabilities file - ex: ./caps/myapp_capabilities.json
  * Update `android->app->local`, `android->appActivity` and `android->appPackage`
* Update `src\test\resources\reportportal.properties` file
* [Run the sample test](https://github.com/anandbagmar/teswiz/blob/main/docs/guides/SampleTests-README.md) 

## Additional information

# [Prerequisites](https://github.com/anandbagmar/teswiz/blob/main/docs/guides/Prerequisites-README.md)

# [Getting started using teswiz](https://github.com/anandbagmar/teswiz/blob/main/docs/guides/GettingStartedUsingTeswiz-README.md)

# [Running the sample tests](https://github.com/anandbagmar/teswiz/blob/main/docs/guides/SampleTests-README.md)

# [Setting up the Hard Gate](https://github.com/anandbagmar/teswiz/blob/main/docs/features/HardGate.md)

# [Visual Test Automation](https://github.com/anandbagmar/teswiz/blob/main/docs/features/RunningVisualTests-README.md)

# [Configure your test execution](https://github.com/anandbagmar/teswiz/blob/main/docs/guides/ConfiguringTestExecution-README.md)

# [Configuration options](https://github.com/anandbagmar/teswiz/blob/main/docs/features/ConfigurationParameters-README.md)
Test execution using teswiz is highly configurable. This enables you to control what type of tests you want to execute, and where (environment, local/cloud), etc, without making changes to your code.

# ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png) Breaking changes in teswiz v0.0.81 ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png)
Refer to the [breaking changes](https://github.com/anandbagmar/teswiz/blob/main/docs/internals/BreakingChanges-README.md) section in [teswiz](https://github.com/anandbagmar/teswiz) repo

# [Feature/Functional coverage](https://github.com/anandbagmar/teswiz/blob/main/docs/internals/FeatureCoverage-README.md) from your test execution

# Using teswiz for your automation?

Simply download the repo as a zip file, or clone it, and run the command:

    ./gradlew clean run

---

## Worked examples

All examples below run the `theapp`, `pdf`, or `api` sample suites bundled in this repo, using the
[`TheApp`](https://github.com/appium-pro/the-app) sample app for the web/android/iOS lanes. Each command is
copy-paste ready and can be run from the repo root.

Prerequisites for the local examples:
* JDK 17+
* `./gradlew publishToMavenLocal` has been run against a local `teswiz` checkout (or you're consuming a published
  `teswiz` release), and `teswizVersion` in [build.gradle](build.gradle) matches
* For Playwright examples: `npm install` (installs `playwright` and `@applitools/eyes-playwright`) and
  `npx playwright install` (installs browser binaries) have been run once
* For Android/iOS examples: a running emulator/simulator (or a connected device) with `TheApp` sample APK/IPA
  available under `src/test/resources/sampleApps`

### 1. se-java (Selenium Java) — single-user web

```bash
CONFIG=./configs/theapp/theapp_local_web_config.properties PLATFORM=web TAG="@theapp2 and @invalidLogin1" ./gradlew run
```

Runs the web login-error scenario using Selenium (`WEB_ENGINE=selenium`, the default).

### 2. pw-java (Playwright Java) — single-user web

```bash
WEB_ENGINE=playwright-java CONFIG=./configs/theapp/theapp_local_web_config.properties PLATFORM=web TAG="@theapp2 and @invalidLogin1" ./gradlew run
```

Same scenario and config as se-java, just overriding `WEB_ENGINE` to run through Playwright's Java bindings instead
of Selenium.

### 3. pw-ts (Playwright TS) — single-user web

```bash
WEB_ENGINE=playwright-ts HEADLESS=true CONFIG=./configs/theapp/theapp_local_web_config.properties PLATFORM=web TAG="@theapp2 and @invalidLogin1 and @playwright-phase1" ./gradlew run
```

Routes screen actions through the TypeScript screen modules under
[`src/test/resources/playwright/screens/theapp`](src/test/resources/playwright/screens/theapp) via a Node worker
process (see [`playwright/worker.mjs`](playwright/worker.mjs)). Requires `npm install` to have been run first.

### 4. android (Appium Android) — single-user

```bash
CONFIG=./configs/theapp/theapp_local_android_config.properties PLATFORM=android TAG="@theapp2 and @invalidLogin1" ./gradlew run
```

Runs the same login-error scenario against the Android build of `TheApp` on a local emulator/device.

### 5. ios (Appium iOS) — single-user

```bash
CONFIG=./configs/theapp/theapp_local_ios_config.properties PLATFORM=iOS TAG="@theapp2 and @invalidLogin1" ./gradlew run
```

Runs the same login-error scenario against the iOS build of `TheApp` on a local simulator/device.

### 6. multi-platform — Android app and Web browser in the same test

```bash
CONFIG=./configs/theapp/theapp_local_android_config.properties TAG="@multiuser-android-web and @theapp5" ./gradlew run
```

One persona ("I") drives the Android app while another persona ("You") drives the web app, within the same
scenario — demonstrating orchestration across two platforms in a single test run.

### 7. multi-user — two users on the same platform (web)

```bash
CONFIG=./configs/theapp/theapp_local_web_config.properties TAG="@multiuser-web and @theapp7" ./gradlew run
```

Two personas ("I" and "You") each drive their own web browser session within the same scenario.

### 8. multi-user & multi-platform — multiple users across platforms

```bash
CONFIG=./configs/theapp/theapp_local_android_config.properties TAG="@multiuser-android-web and @theapp6" ./gradlew run
```

Three personas ("I", "You", "They") span two platforms (Android + web) in a single scenario — the same
orchestration mechanism as lane 6, scaled up to more users.

### 9. api — REST API client tests

```bash
CONFIG=./configs/api_local_config.properties TAG=restUserAPI PLATFORM=api ./gradlew run
```

Runs the JSONPlaceholder CRUD workflow (create/update/delete a post). See also
[`weatherAPI.feature`](src/test/resources/com/znsio/sample/e2e/features/weatherAPI.feature):

```bash
CONFIG=./configs/api_local_config.properties TAG=weatherAPI PLATFORM=api ./gradlew run
```

### 10. pdf — PDF reading and validation tests

```bash
CONFIG=./configs/pdf/pdf_local_web_config.properties PLATFORM=web TAG=@validatePDF ./gradlew run
CONFIG=./configs/pdf/pdf_local_android_config.properties PLATFORM=android TAG=@validatePDF ./gradlew run
```

Validates a PDF document opened from a web/Android scenario using Applitools. For a standalone PDF check (no
app/browser involved):

```bash
CONFIG=./configs/pdf/local_pdf_config.properties PLATFORM=pdf TAG=@standalone ./gradlew run
```

---

### Known limitations

* **pw-ts and the new `ScreenRegistry` convention**: teswiz 1.0.28 introduced a package-convention-based screen
  resolver (`com.znsio.teswiz.screen.ScreenRegistry`) that automatically wires up Playwright TS screen modules. It
  works with screen contracts declared under any package, as long as the package contains a segment literally named
  `screen` (e.g. `com.znsio.sample.e2e.screen.<domain>`) — this repo's `theapp` screens already follow that
  convention. If you add new screens under a different naming scheme, make sure a `screen` package segment is
  present or the resolver will raise a clear error telling you what it expected.
* **pw-ts opens a visual (Applitools) session unconditionally.** Unlike se-java/pw-java, which skip Applitools
  entirely when `IS_VISUAL=false`, the Playwright TS driver path currently opens a visual session regardless of that
  flag. Set `TESWIZ_APPLITOOLS_API_KEY` in your environment before running pw-ts examples, even if you don't care
  about visual checks — otherwise the run fails with `apiKey must be an alphanumeric string. Received not-set`.
* **weatherAPI.feature** depends on a third-party geocoding API key baked into `testData.json`. If that key is
  missing/expired you'll see `expected: 200 but was: 401` on the "fetch location coordinates" step — the
  `jsonPlaceHolderCRUD.feature` scenario in the same `api` lane doesn't have this dependency and is a good first
  smoke test.
