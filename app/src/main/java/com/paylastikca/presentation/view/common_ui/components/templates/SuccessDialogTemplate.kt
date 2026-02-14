package com.paylastikca.presentation.view.common_ui.components.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.layout.scaled

@Composable
fun SuccessDialogTemplate(
    title: String,
    message: String?,
    buttonText: String,
    onButtonClick: () -> Unit,
    onDismiss: (() -> Unit)? = null,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.onSurface.copy(alpha = 0.06f))
            .clickable(enabled = onDismiss != null) { onDismiss?.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.82f)
                .background(colors.surface, RoundedCornerShape(16.dp))
                .padding(16.dp.scaled(scale))
                .clickable(enabled = false) { }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                // ikon yerine şimdilik emoji (KMM için problemsiz)
                Text(
                    text = "✅",
                    fontSize = 34.sp.scaled(scale)
                )

                Spacer(Modifier.height(10.dp.scaled(scale)))

                Text(
                    text = title,
                    fontSize = 18.sp.scaled(scale),
                    fontWeight = FontWeight.Black,
                    color = colors.onSurface
                )

                if (!message.isNullOrBlank()) {
                    Spacer(Modifier.height(6.dp.scaled(scale)))
                    Text(
                        text = message,
                        fontSize = 12.sp.scaled(scale),
                        color = colors.onSurfaceVariant
                    )
                }

                Spacer(Modifier.height(16.dp.scaled(scale)))

                Button(
                    onClick = onButtonClick,
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .height(44.dp.scaled(scale)),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = colors.onPrimary
                    )
                ) {
                    Text(
                        text = buttonText,
                        fontWeight = FontWeight.Black,
                        fontSize = 13.sp.scaled(scale)
                    )
                }
            }
        }
    }
}
