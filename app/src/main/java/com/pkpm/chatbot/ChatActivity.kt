package com.pkpm.chatbot

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.res.colorResource
import com.pkpm.chatbot.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    private var _binding: ActivityChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        window.statusBarColor = getColor(R.color.white)
        hideNavigationBar()

        val chatContext = listOf(
            GeminiContent(
                role = "user",
                text = """
                   //Input your rules here//
                    """.trimIndent()
            )
        )

        binding.composeView.setContent {
            GeminiChatView(
                apiKey = "//Input your ApiKey here//",
                appThemColor = colorResource(R.color.blue_l),
                chatContext = listOf(
                    GeminiContent(
                        role = "user",
                        text = chatContext.toString()
                    )
                )
            )
        }
    }

    private fun hideNavigationBar() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                )
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideNavigationBar()
        }
    }
}
