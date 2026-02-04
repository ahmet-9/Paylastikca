package com.paylastikca.presentation.view.common_ui.components.platform

import androidx.compose.runtime.Composable


@Composable
fun PlatformDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    AndroidPlatformDialog(onDismissRequest = onDismissRequest, content = content)
}
