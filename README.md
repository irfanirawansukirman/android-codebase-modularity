# Android Codebase Modularity

![GitHub stars](https://img.shields.io/github/stars/irfanirawansukirman/android-codebase-modularity?style=social)
![GitHub forks](https://img.shields.io/github/forks/irfanirawansukirman/android-codebase-modularity?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/irfanirawansukirman/android-codebase-modularity?style=social)

## Description
MarvelHeroes is a demo application based on modern Android application tech-stacks and MVVM architecture.
Fetching data from the network and integrating persisted data in the database via repository pattern.

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - MVVM Architecture (View - View Binding - ViewModel - Model)
  - Repository pattern
  - Clean Architecture
  - Modularization
- [Koin](https://github.com/InsertKoinIO/koin) - dependency injection.
- [Retrofit2](https://github.com/square/retrofit) - construct the REST APIs.
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging and mocking web server.
- [Glide](https://github.com/bumptech/glide) - loading images.
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization) - kotlin multiplatform / multi-format serialization.
