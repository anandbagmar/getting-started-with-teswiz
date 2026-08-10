# Implementation Plan - Update `getting-started-with-teswiz` Examples & README

This plan outlines the steps to synchronize the features, screens, steps, and configs of the standalone starter repository `anandbagmar/getting-started-with-teswiz` with the latest changes in `anandbagmar/teswiz`, and update its documentation to provide explicit, out-of-the-box examples for all support lanes.

---

## User Review Required

> [!IMPORTANT]
> **Dependency Resolution and Publication:**
> Because `getting-started-with-teswiz` downloads and runs the `teswiz` framework JAR, we need to ensure the local changes we made to `teswiz` (version `1.0.28`) are published locally first. We will build and publish `teswiz` to Maven Local (`./gradlew publishToMavenLocal`), then set `teswizVersion = "1.0.28"` in the `getting-started` build file.

---

## Proposed Changes

### 1. Build & Dependencies Configuration

#### [MODIFY] [build.gradle](file:///Users/anand.bagmar/projects/znsio/getting-started-with-teswiz/build.gradle)
- Update `teswizVersion` to `1.0.28` to consume the latest changes (including Playwright TS BrowserStack connection fix).
- Ensure the `run` task is properly configured to pass all system properties and command-line arguments to Cucumber.

#### [MODIFY] [package.json](file:///Users/anand.bagmar/projects/znsio/getting-started-with-teswiz/package.json)
- **Note:** the root `package.json` here currently mirrors teswiz's own manifest (`"name": "teswiz"`) and has **no** `playwright` or `@applitools/eyes-playwright` entries at all — these are not present to "lock," they must be added fresh.
- Add `playwright` devDependency at `1.49.0` (matching the Java version and local setup), copied from `../teswiz/package.json`.
- Add `@applitools/eyes-playwright` at `^1.48.0` for full compatibility.

### 2. Capability & Configuration Files

#### [MODIFY] [caps/](file:///Users/anand.bagmar/projects/znsio/getting-started-with-teswiz/caps)
- Diff and merge (not mirror) the `caps` directory against `teswiz` to support Selenium, Playwright Java, and Playwright TS on local and BrowserStack environments. This repo has sample-specific caps (e.g. `ajio`, `theapp`, `pdf`) that don't exist in teswiz's own `caps/` dir — a wholesale copy would delete them. Only add/update files that correspond to shared/example apps.

#### [MODIFY] [configs/](file:///Users/anand.bagmar/projects/znsio/getting-started-with-teswiz/configs)
- Diff and merge (not mirror) the `configs` directory to ensure all properties files contain matching execution parameters (like `WEB_ENGINE=selenium`), preserving sample-specific config files that have no counterpart in teswiz.

### 3. Features & Test Resources

#### [NEW] [src/test/resources/playwright/screens](file:///Users/anand.bagmar/projects/znsio/getting-started-with-teswiz/src/test/resources/playwright/screens)
- **Note:** there is currently no Playwright TS infrastructure in this repo at all (no `playwright/` directory, no `.ts` screens). This is net-new bootstrapping, not a sync — treat it as introducing new tooling wiring (deps, config, scripts) in addition to copying the screen files from `../teswiz/playwright/screens`.

#### [MODIFY] [src/test/resources/com/znsio/sample/e2e/features](file:///Users/anand.bagmar/projects/znsio/getting-started-with-teswiz/src/test/resources/com/znsio/sample/e2e/features)
- Synchronize and update all `.feature` files (e.g. `theapp.feature`, `googlesearch.feature`, `indigo.feature`, `jiomeet.feature`, `dineout.feature`, `pdf.feature`) to ensure step names and tags match the ones in `teswiz`.

### 4. Step Definitions & Screens (Java)

#### [MODIFY] [src/test/java/com/znsio/sample/e2e/steps](file:///Users/anand.bagmar/projects/znsio/getting-started-with-teswiz/src/test/java/com/znsio/sample/e2e/steps)
- **Note:** this directory already has ~20 step-definition files (Dineout, Indigo, JioMeet, PDF, VodQA, etc.) — this is a diff-and-merge against `teswiz` (`src/test/java/com/znsio/teswiz/steps`), not a bulk copy. Update package declarations to `package com.znsio.sample.e2e.steps` and resolve imports to target `com.znsio.sample.e2e`, without clobbering sample-only step files that have no teswiz counterpart.

#### [MODIFY] [src/test/java/com/znsio/sample/e2e/screen](file:///Users/anand.bagmar/projects/znsio/getting-started-with-teswiz/src/test/java/com/znsio/sample/e2e/screen)
- **Note:** this directory already has subpackages (`jiomeet`, `vodqa`, `autoscroll`, `ios`, etc.) — this is a diff-and-merge against `teswiz` (`src/test/java/com/znsio/teswiz/screen`), not a bulk copy. Adapt packages and class imports to use `com.znsio.sample.e2e.screen`, preserving sample-only screens.

### 5. Documentation Update

#### [MODIFY] [README.md](file:///Users/anand.bagmar/projects/znsio/getting-started-with-teswiz/README.md)
- Restructure the README to provide explicit copy-paste Gradle execution commands and explanations for each of the following examples:
  1. **se-java (Selenium Java):** Run single-user web using Selenium.
  2. **pw-java (Playwright Java):** Run single-user web using Playwright Java.
  3. **pw-ts (Playwright TS):** Run single-user web using Playwright TS.
  4. **android (Appium Android):** Run single-user Android app tests.
  5. **ios (Appium iOS):** Run single-user iOS app tests.
  6. **multi-platform:** Run mixed platform tests (e.g. Android app and Web browser in the same test).
  7. **multi-user:** Run multi-user tests on the same platform (e.g. two web users).
  8. **multi-user & multi-platform:** Run multi-user tests across platforms (e.g. one Android user, one Web user).
  9. **api:** Run REST API client tests.
  10. **pdf:** Run PDF reading and validation tests.

---

## Verification Plan

### Automated Verification
1. **Publish Local Dependency:** Run `./gradlew publishToMavenLocal` in `teswiz` to build version `1.0.28`.
2. **Build Verification:** Run `./gradlew clean compileJava` in `getting-started-with-teswiz` to ensure code compiles.
3. **Execution Verification:** Verify sample commands for:
   - `se-java` (using local Chrome)
   - `pw-java` (using local Chrome)
   - `pw-ts` (using local Chrome)
   - `api` / `pdf` tests
   - `android`, `ios`, `multi-platform`, `multi-user`, and `multi-user & multi-platform` examples are **documented only** (README commands provided) and not executed as part of this verification pass — no device/emulator/BrowserStack session is exercised. Call this out explicitly rather than omitting it silently.
