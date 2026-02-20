package com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.paylastikca.presentation.view.common_ui.components.layout.scaled


@Composable
private fun DeleteConfirmationDialogTemplate(
    scale: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Dialog(
        onDismissRequest = { /* block */ },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        // overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.onSurface.copy(alpha = 0.06f)),
            contentAlignment = Alignment.Center
        ) {
            // dialog card container
            val dialogShape = RoundedCornerShape(16.dp)
            Box(
                modifier = modifier
                    .fillMaxWidth(0.82f)
                    .background(colors.surface, dialogShape)
                    .padding(16.dp.scaled(scale))
            ) {
                content()
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialogTemplate(
    title: String,
    message: String,
    confirmText: String,
    cancelText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    DeleteConfirmationDialogTemplate(scale = scale, modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                tint = colors.error,
                modifier = Modifier.size(66.dp.scaled(scale))
            )

            Spacer(Modifier.height(10.dp.scaled(scale)))

            Text(
                text = title,
                fontSize = 19.sp.scaled(scale),
                fontWeight = FontWeight.Black,
                color = colors.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            if (message.isNotBlank()) {
                Spacer(Modifier.height(10.dp.scaled(scale)))
                Text(
                    text = message,
                    fontSize = 17.sp.scaled(scale),
                    color = colors.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(16.dp.scaled(scale)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp.scaled(scale))
            ) {
                ActionButton(
                    text = confirmText,
                    onClick = onConfirm,
                    height = 44.dp.scaled(scale),
                    shapeRadius = 12.dp,
                    containerColor = colors.error,
                    contentColor = colors.onError,
                    textSize = 20.sp.scaled(scale),
                    modifier = Modifier.weight(1f)
                )

                ActionButton(
                    text = cancelText,
                    onClick = onCancel,
                    height = 44.dp.scaled(scale),
                    shapeRadius = 12.dp,
                    containerColor = colors.primary,
                    contentColor = colors.onPrimary,
                    textSize = 20.sp.scaled(scale),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun DeleteSuccessDialog(
    title: String,
    message: String?,
    buttonText: String,
    onButtonClick: () -> Unit,
    scale: Float,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    DeleteConfirmationDialogTemplate(scale = scale, modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = colors.tertiary,
                modifier = Modifier.size(60.dp.scaled(scale))
            )

            Spacer(Modifier.height(22.dp.scaled(scale)))

            Text(
                text = title,
                fontSize = 21.sp.scaled(scale),
                fontWeight = FontWeight.Black,
                color = colors.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            if (!message.isNullOrBlank()) {
                Spacer(Modifier.height(6.dp.scaled(scale)))
                Text(
                    text = message,
                    fontSize = 14.sp.scaled(scale),
                    color = colors.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(22.dp.scaled(scale)))

            ActionButton(
                text = buttonText,
                onClick = onButtonClick,
                height = 44.dp.scaled(scale),
                shapeRadius = 12.dp,
                containerColor = colors.primary,
                contentColor = colors.onPrimary,
                textSize = 13.sp.scaled(scale),
                modifier = Modifier.fillMaxWidth(0.55f)
            )
        }
    }
}


@Composable
private fun ActionButton(
    text: String,
    onClick: () -> Unit,
    height: Dp,
    shapeRadius: Dp,
    containerColor: androidx.compose.ui.graphics.Color,
    contentColor: androidx.compose.ui.graphics.Color,
    textSize: androidx.compose.ui.unit.TextUnit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(height),
        shape = RoundedCornerShape(shapeRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Black,
            fontSize = textSize,
            textAlign = TextAlign.Center
        )
    }
}