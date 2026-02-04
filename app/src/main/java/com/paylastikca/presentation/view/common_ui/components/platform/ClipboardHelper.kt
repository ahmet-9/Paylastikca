package com.paylastikca.presentation.view.common_ui.components.platform

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

interface ClipboardHelper {
    fun copy(text: String)
}

@Composable
fun rememberClipboardHelper(): ClipboardHelper {
    val context = LocalContext.current
    return object : ClipboardHelper {
        override fun copy(text: String) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("text", text))
        }
    }
}
