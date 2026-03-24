# AIStudyBuddy

AI-powered study tutor that explains topics, generates quizzes, and tracks your progress.

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?logo=jetpackcompose&logoColor=white)
![Gemini API](https://img.shields.io/badge/AI-Gemini%20API-1A73E8)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

## Demo

![Demo GIF](./docs/demo.gif)

_Replace this with a screen recording — use Android Studio's built-in recorder._

## Features

- **AI Chat Tutor**: conversational explanations using Gemini Pro with persistent session history.
- **Quiz Generator**: prompt-engineered JSON output parsed into interactive 5-question quizzes.
- **Progress History**: Room Database persistence for sessions and scores across app restarts.
- **Text-to-Speech**: on-device TTS reads AI answers aloud.
- **Dark Mode + Material3**: dark mode support with Material3 dynamic color.

## Tech Stack

| Layer | Technology |
|---|---|
| UI | Kotlin, Jetpack Compose, Material3, Navigation Compose |
| State | ViewModel, StateFlow, Compose State |
| AI | Google Gemini API (`com.google.ai.client.generativeai`) |
| Local DB | Room (`room-runtime`, `room-ktx`) |
| Architecture | MVVM + Repository Pattern |

## Architecture

AIStudyBuddy follows an **MVVM + Repository** architecture. UI composables observe `StateFlow`/Compose state from ViewModels, while ViewModels coordinate business logic and asynchronous work using coroutines. The Repository layer encapsulates Gemini interactions and parsing logic, and Room DAOs handle local persistence so chat/quiz history survives app restarts.

## Getting Started

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd AIStudyBuddy
   ```
2. **Add your Gemini API key** in `local.properties`:
   ```properties
   GEMINI_API_KEY=your_api_key_here
   ```
3. **Run the app** in Android Studio:
   - Open the project.
   - Sync Gradle.
   - Select an emulator/device.
   - Click **Run**.

## What I Learned

- Effective prompt engineering is critical for reliable structured JSON output from LLMs.
- MVVM with a repository boundary keeps UI logic clean and testable.
- Coroutines + StateFlow simplify async UI updates and lifecycle-safe state handling.

## License

This project is licensed under the MIT License.
