package com.paylastikca.presentation.view.common_ui.components.verification.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpInput(
    value: String,
    length: Int = 6,
    onValueChange: (String) -> Unit,
    borderColor: Color,
) {
    val colors = MaterialTheme.colorScheme
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusRequester.requestFocus() },
        contentAlignment = Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            repeat(length) { index ->
                val char = value.getOrNull(index)?.toString() ?: ""
                Box(
                    modifier = Modifier
                        .size(width = 34.dp, height = 44.dp)
                        .border(1.5.dp, borderColor, RoundedCornerShape(10.dp))
                        .background(colors.surface, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = char,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = colors.onSurface
                    )
                }
            }
        }

        BasicTextField(
            value = value,
            onValueChange = {
                val filtered = it.filter(Char::isDigit).take(length)
                onValueChange(filtered)
                if (filtered.length == length) focusManager.clearFocus()
            },
            modifier = Modifier
                .size(1.dp)
                .focusRequester(focusRequester),
            textStyle = TextStyle(fontSize = 1.sp)
        )
    }

    LaunchedEffect(Unit) { focusRequester.requestFocus() }
}
