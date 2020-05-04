# Android Codebase Modularity

![GitHub stars](https://img.shields.io/github/stars/irfanirawansukirman/android-codebase-modularity?style=social)
![GitHub forks](https://img.shields.io/github/forks/irfanirawansukirman/android-codebase-modularity?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/irfanirawansukirman/android-codebase-modularity?style=social)

## Description
Lorem is a demo application based on modern Android application tech-stacks and MVVM architecture.
Fetching data from the network and integrating persisted data in the database via repository pattern.

## Quick start
This is a normal lorem app. You should follow the instructions in the [official documentation](https://google.com).
This project uses **Lorem** (business logic component) to separate the business logic with UI itself.
It's recommended to do self-study about it before jumping into the project [here](https://google.com/).
And also on this project uses **Modularization** approach to separate each feature ([core](https://google.com), [shared](https://google.com), [ui](https://google.com) modules).
You can do self-study about it in [here](https://google.com).

## Modularization Structure 🔥

    # Root Project
    .
    ├── lorem                   # Name of module (new package)
    │   ├── lorem               # Business logic component.
    │   └── lorem            # Remote Data Handlers
    |       ├── lorem            # Retrofit API for remote end point, model using Equatable.
    │       └── lorem     # Single source of data and for handling data from network to cache.
    |
    ├── lorem                    # Name of module (default from lorem)
    │   └── lorem                 # Activity/View layer
    |
    └── lorem                 # Name of module (new package)
        ├── lorem             # Common shared.
        │   ├── lorem        # Custom function, extension, etc which can be used repeatedly on each method.
        │   ├── lorem         # Custom style that will be used on each widget.
        │   └── lorem          # Utility classes.
        └── lorem             # Custom widget which can be used repeatedly.

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