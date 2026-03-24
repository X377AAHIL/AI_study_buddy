package com.student.aistudybuddy.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import java.util.UUID

class TTSManager(
    context: Context,
) : TextToSpeech.OnInitListener {
    private val tts = TextToSpeech(context.applicationContext, this)
    private var isInitialized = false

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    init {
        tts.setOnUtteranceProgressListener(
            object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    _isSpeaking.value = true
                }

                override fun onDone(utteranceId: String?) {
                    _isSpeaking.value = false
                }

                @Deprecated("Required by UtteranceProgressListener for backward compatibility.")
                override fun onError(utteranceId: String?) {
                    _isSpeaking.value = false
                }

                override fun onError(utteranceId: String?, errorCode: Int) {
                    _isSpeaking.value = false
                }
            },
        )
    }

    override fun onInit(status: Int) {
        isInitialized = status == TextToSpeech.SUCCESS
        if (isInitialized) {
            tts.language = Locale.getDefault()
        }
    }

    fun speak(text: String) {
        if (!isInitialized || text.isBlank()) return

        val utteranceId = UUID.randomUUID().toString()
        val result = tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
        if (result != TextToSpeech.SUCCESS) {
            _isSpeaking.value = false
        }
    }

    fun stop() {
        tts.stop()
        _isSpeaking.value = false
    }

    fun shutdown() {
        tts.stop()
        tts.shutdown()
        isInitialized = false
        _isSpeaking.value = false
    }
}
