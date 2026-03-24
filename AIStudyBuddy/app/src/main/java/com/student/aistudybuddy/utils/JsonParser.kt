package com.student.aistudybuddy.utils

import com.student.aistudybuddy.data.model.QuizQuestion
import org.json.JSONArray

object JsonParser {
    fun parseQuizJson(json: String): List<QuizQuestion> {
        val normalizedJson = extractJsonArray(json)

        return runCatching {
            val rootArray = JSONArray(normalizedJson)
            val questions = mutableListOf<QuizQuestion>()

            for (i in 0 until rootArray.length()) {
                val item = rootArray.optJSONObject(i) ?: continue
                val question = item.optString("question").trim()
                val optionsArray = item.optJSONArray("options") ?: JSONArray()
                val correctAnswerIndex = item.optInt("correctAnswerIndex", -1)

                val options = mutableListOf<String>()
                for (j in 0 until optionsArray.length()) {
                    options += optionsArray.optString(j)
                }

                val isValidQuestion = question.isNotBlank()
                val isValidOptions = options.isNotEmpty()
                val isValidIndex = correctAnswerIndex in options.indices

                if (isValidQuestion && isValidOptions && isValidIndex) {
                    questions += QuizQuestion(
                        question = question,
                        options = options,
                        correctAnswerIndex = correctAnswerIndex,
                    )
                }
            }

            questions
        }.getOrElse {
            emptyList()
        }
    }

    private fun extractJsonArray(raw: String): String {
        val start = raw.indexOf('[')
        val end = raw.lastIndexOf(']')

        if (start == -1 || end == -1 || end <= start) {
            return raw
        }

        return raw.substring(start, end + 1)
    }
}
