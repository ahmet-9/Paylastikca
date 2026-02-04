package com.paylastikca.presentation.view.common_ui.components.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
internal fun AndroidPlatformDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        content()
    }
}
