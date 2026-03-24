package com.student.aistudybuddy.data.repository

import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.student.aistudybuddy.BuildConfig
import com.student.aistudybuddy.data.model.QuizQuestion
import com.student.aistudybuddy.utils.JsonParser

class GeminiRepository {
    private val apiKey: String = BuildConfig.GEMINI_API_KEY.trim()
    private val modelName: String = BuildConfig.GEMINI_MODEL.trim().ifBlank { DEFAULT_MODEL_NAME }
    private val model by lazy {
        GenerativeModel(
            modelName = modelName,
            apiKey = apiKey,
        )
    }
    private var chatSession: Chat? = null

    suspend fun chat(userMessage: String): Result<String> {
        if (apiKey.isBlank()) {
            return Result.failure(
                IllegalStateException("Missing GEMINI_API_KEY in local.properties."),
            )
        }

        return try {
            val response = getOrCreateChatSession().sendMessage(userMessage)
            val responseText = response.text?.trim().orEmpty()

            if (responseText.isBlank()) {
                Result.failure(IllegalStateException("Gemini returned an empty response."))
            } else {
                Result.success(responseText)
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    suspend fun generateQuiz(topic: String): Result<List<QuizQuestion>> {
        if (apiKey.isBlank()) {
            return Result.failure(
                IllegalStateException("Missing GEMINI_API_KEY in local.properties."),
            )
        }

        val prompt =
            """
            Generate exactly 5 multiple choice questions about [$topic]. 
            Return ONLY a JSON array, no explanation, no markdown, no code block.
            Format: [{\"question\":\"...\",\"options\":[\"A\",\"B\",\"C\",\"D\"],\"correctAnswerIndex\":0}]
            """.trimIndent()

        return try {
            val response = model.generateContent(prompt)
            val responseText = response.text?.trim().orEmpty()
            val parsedQuestions = JsonParser.parseQuizJson(responseText)

            if (parsedQuestions.isEmpty()) {
                Result.failure(IllegalArgumentException("Failed to parse quiz JSON from Gemini response."))
            } else {
                Result.success(parsedQuestions)
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    fun resetChat() {
        chatSession = createNewChatSession()
    }

    private fun getOrCreateChatSession(): Chat {
        return chatSession ?: createNewChatSession().also { created ->
            chatSession = created
        }
    }

    private fun createNewChatSession(): Chat {
        return model.startChat(
            history = listOf(
                content("user") {
                    text(SYSTEM_INSTRUCTION)
                },
            ),
        )
    }

    private companion object {
        const val DEFAULT_MODEL_NAME = "gemini-1.5-flash"
        const val SYSTEM_INSTRUCTION =
            "You are Study Buddy, a friendly and patient AI tutor. Give clear, concise explanations suitable for students. Use examples. Keep responses under 150 words unless the student asks for more detail."
    }
}
