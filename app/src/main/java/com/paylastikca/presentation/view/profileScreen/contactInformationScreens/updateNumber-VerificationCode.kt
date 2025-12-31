package com.paylastikca.presentation.view.profileScreen.contactInformationScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun updateNumberVerificationCode(
    phone: String,
    code: String,
    secondsLeft: Int,
    errorMessage: String?,
    onCodeChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onResend: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Telefon Doğrulaması\nBekleniyor",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = buildAnnotatedString {
                        append(formatTrPhonePretty(phone))
                        addStyle(
                            SpanStyle(fontWeight = FontWeight.ExtraBold),
                            0,
                            length
                        )
                        append(" numarasına doğrulama kodu SMS olarak gönderildi. Lütfen kodu giriniz.")
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                if (!errorMessage.isNullOrBlank()) {
                    Spacer(Modifier.height(10.dp))
                    Surface(
                        color = Color(0xFFE53935),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(36.dp)
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(
                                text = errorMessage,
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))


                OtpInput(
                    value = code,
                    length = 6,
                    onValueChange = onCodeChange
                )

                Spacer(Modifier.height(12.dp))

                Text(secondsLeft.toString(), style = MaterialTheme.typography.headlineMedium)

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7200),
                        contentColor = Color.White,
                    )
                ) { Text("onayla") }

                Spacer(Modifier.height(6.dp))

                TextButton(
                    onClick = onResend,
                    enabled = secondsLeft == 0,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (secondsLeft == 0) Color.Black else Color.Gray
                    )
                ) {
                    Text("kodu tekrar gönder")
                }
            }
        }
    }
}

