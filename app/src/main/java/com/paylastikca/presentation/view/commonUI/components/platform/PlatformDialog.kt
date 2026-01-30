package com.paylastikca.presentation.view.commonUI.components.platform

import androidx.compose.runtime.Composable


@Composable
fun PlatformDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    AndroidPlatformDialog(onDismissRequest = onDismissRequest, content = content)
}
