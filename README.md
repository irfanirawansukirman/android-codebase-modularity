# Android Codebase Modularity

![GitHub stars](https://img.shields.io/github/stars/irfanirawansukirman/android-codebase-modularity?style=social)
![GitHub forks](https://img.shields.io/github/forks/irfanirawansukirman/android-codebase-modularity?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/irfanirawansukirman/android-codebase-modularity?style=social)

## Description
This is a demo movie app based on modern Android application tech-stacks and MVVM architecture.
Fetching data from the network and integrating persisted data in the database via repository pattern.

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the interface layer.
- Architecture
  - MVVM Architecture (View - View Binding - ViewModel - Model)
  - Repository pattern
  - [Clean Architecture](https://five.agency/android-architecture-part-1-every-new-beginning-is-hard/) - Part 1 until Part 5
  - [Modularization](https://proandroiddev.com/intro-to-app-modularization-42411e4c421e) - Intro app modularization 
- [Koin](https://github.com/InsertKoinIO/koin) - dependency injection.
- [Retrofit2](https://github.com/square/retrofit) - construct the REST APIs.
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging and mocking web server.
- [Glide](https://github.com/bumptech/glide) - loading images.
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization) - kotlin multiplatform / multi-format serialization.
- [CI Gitlab](https://medium.com/kode-dan-kodean/menggunakan-gitlab-ci-untuk-build-android-apk-405217cf525d) - Android CI using Gitlab Runner.

## License

```
MIT License

Copyright (c) [2020] [Irfan Irawan Sukirman]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```