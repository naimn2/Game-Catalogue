# Game Catalogue

A comprehensive Android application for browsing games, built with modern Android development tools and best practices.

## 🎬 Demo

https://github.com/user-attachments/assets/4fbff51b-9d4c-4e0b-a346-5a1aec42ba83

https://github.com/user-attachments/assets/cab2efc6-0869-4fd2-8d19-ba54eb110c3a

## 🏗 Architecture
This project implements **Clean Architecture**, ensuring a clear separation of concerns:
- **Data Layer:** Handles data retrieval from local (Room) and remote (Retrofit) sources.
- **Domain Layer:** Contains business logic and use cases.
- **Presentation Layer:** MVVM pattern using ViewModel and ViewBinding.

## 🧱 Modularization
The project is divided into several modules to improve maintainability and build speed:
- `:app` - The main application module containing the UI for the main features.
- `:core` - Contains shared code, data sources, network configuration, and utilities.
- `:favorite` - A **Dynamic Feature Module** that handles the favorite games feature, loaded on demand.

## 💉 Dependency Injection
Uses **Hilt (Dagger)** for dependency injection, providing a robust way to manage object lifecycles and simplify testing.

## 🌊 Reactive Programming
Implemented using **Kotlin Coroutines and Flow** for asynchronous operations and reactive data streams, ensuring a smooth and responsive UI.

## 🛡 Security
Security is a top priority in this project:
- **Obfuscation:** ProGuard/R8 is enabled to protect the source code from reverse engineering.
- **Database Encryption:** Uses **SQLCipher** to encrypt the local Room database.
- **SSL Pinning:** Implemented in the `NetworkModule` using `CertificatePinner` to prevent man-in-the-middle (MITM) attacks.

## 🚀 Performance
- **LeakCanary:** Integrated to detect and help fix memory leaks during development.
- **Optimization:** Image loading is handled efficiently using **Glide**.

## 🛠 Tech Stack
- Kotlin
- Jetpack (Room, ViewModel, LiveData, Navigation)
- Retrofit & OkHttp
- Hilt
- Coroutines & Flow
- SQLCipher
- LeakCanary
- Glide
