package com.paylastikca.presentation.view.common_ui.components.platform

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

interface ShareHelper {
    fun shareText(text: String)
}

@Composable
fun rememberShareHelper(): ShareHelper {
    val context = LocalContext.current
    return object : ShareHelper {
        override fun shareText(text: String) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }
            context.startActivity(Intent.createChooser(intent, null))
        }
    }
}
