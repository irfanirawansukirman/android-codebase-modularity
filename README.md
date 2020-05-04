# Android Codebase Modularity

![GitHub stars](https://img.shields.io/github/stars/irfanirawansukirman/android-codebase-modularity?style=social)
![GitHub forks](https://img.shields.io/github/forks/irfanirawansukirman/android-codebase-modularity?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/irfanirawansukirman/android-codebase-modularity?style=social)

## Description
MarvelHeroes is a demo application based on modern Android application tech-stacks and MVVM architecture.
Fetching data from the network and integrating persisted data in the database via repository pattern.

## Quick start
This is a normal flutter app. You should follow the instructions in the [official documentation](https://flutter.io/docs/get-started/install).
This project uses **BLoC** (business logic component) to separate the business logic with UI itself.
It's recommended to do self-study about it before jumping into the project [here](https://bloclibrary.dev/).
And also on this project uses **Modularization** approach to separate each feature ([core](https://github.com/rrifafauzikomara/MovieCatalogue/tree/master/core), [shared](https://github.com/rrifafauzikomara/MovieCatalogue/tree/master/shared), [ui](https://github.com/rrifafauzikomara/MovieCatalogue/tree/master/lib/ui) modules).
You can do self-study about it in [here](https://medium.com/@rifafauzi6/mastering-flutter-modularization-in-several-ways-c5db5ff5a54a).

## Modularization Structure 🔥

    # Root Project
    .
    ├── core                   # Name of module (new package)
    │   ├── bloc               # Business logic component.
    │   └── network            # Remote Data Handlers
    |       ├── api            # Retrofit API for remote end point, model using Equatable.
    │       └── repository     # Single source of data and for handling data from network to cache.
    |
    ├── lib                    # Name of module (default from Flutter)
    │   └── ui                 # Activity/View layer
    |
    └── shared                 # Name of module (new package)
        ├── common             # Common shared.
        │   ├── helpers        # Custom function, extension, etc which can be used repeatedly on each method.
        │   ├── styles         # Custom style that will be used on each widget.
        │   └── utils          # Utility classes.
        └── widget             # Custom widget which can be used repeatedly.

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
