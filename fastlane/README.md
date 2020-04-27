fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew cask install fastlane`

# Available Actions
## Android
### android run_test
```
fastlane android run_test
```
Run Test
### android run_check
```
fastlane android run_check
```
Run Internal Lint
### android run_debug_jacoco_unit_test
```
fastlane android run_debug_jacoco_unit_test
```
Run Dev Debug Jacoco Unit Test
### android run_detekt
```
fastlane android run_detekt
```
Run Test
### android run_sonarqube
```
fastlane android run_sonarqube
```
Run Test
### android distribute
```
fastlane android distribute
```
Deploy latest Beta build to Firebase App Distribution

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
