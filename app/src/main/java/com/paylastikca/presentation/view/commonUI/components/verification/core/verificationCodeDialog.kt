package com.paylastikca.presentation.view.commonUI.components.verification.core

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun VerificationCodeDialog(
    title: String,
    boldText: String,
    messageAfterBold: String,
    code: String,
    secondsLeft: Int,
    errorMessage: String?,
    onCodeChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onResend: () -> Unit,
    codeLength: Int = 6
) {
    val colors = MaterialTheme.colorScheme

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    title,
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = buildAnnotatedString {
                        append(boldText)
                        addStyle(
                            style = SpanStyle(fontWeight = FontWeight.ExtraBold),
                            start = 0,
                            end = boldText.length
                        )
                        append(messageAfterBold)
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                if (!errorMessage.isNullOrBlank()) {
                    Spacer(Modifier.height(10.dp))
                    ErrorBanner(message = errorMessage)
                }

                Spacer(Modifier.height(12.dp))

                OtpInput(
                    value = code,
                    length = codeLength,
                    onValueChange = onCodeChange,
                    borderColor = colors.primary
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    secondsLeft.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = colors.onPrimary
                    )
                ) { Text("Onayla") }

                Spacer(Modifier.height(6.dp))

                TextButton(
                    onClick = onResend,
                    enabled = secondsLeft == 0,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (secondsLeft == 0) colors.onSurface else colors.onSurfaceVariant
                    )
                ) {
                    Text("Kodu tekrar gönder")
                }
            }
        }
    }
}
