package com.paylastikca.presentation.view.profileScreen.contactInformationScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun updateNumber(
    currentPhone: String,
    newPhone: String,
    onNewPhoneChange: (String) -> Unit,
    errorMessage: String?,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {


    val colors = MaterialTheme.colorScheme

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Telefon Güncelle",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Mevcut: $currentPhone",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant,
                    textAlign = TextAlign.Center,

                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = newPhone,
                    onValueChange = { input ->

                        val filtered = input.filter { it.isDigit() || it == '+' }
                        onNewPhoneChange(filtered)
                    },
                    label = { Text("Yeni Telefon Numarası") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colors.outline,
                        unfocusedBorderColor = colors.outlineVariant,
                        cursorColor = colors.primary,
                        focusedLabelColor = colors.primary
                    )
                )

                if (!errorMessage.isNullOrBlank()) {
                    Spacer(Modifier.height(10.dp))
                    Surface(
                        color = colors.error,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 36.dp)
                    ) {
                        Box(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = errorMessage,
                                color = colors.onError,
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }


                Spacer(Modifier.height(8.dp))

                Text(
                    "Kaydet'e bastıktan sonra doğrulama kodu SMS olarak gönderilecektir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.onPrimary,
                            contentColor = colors.primary,
                        ),
                        border = BorderStroke(1.dp, colors.primary)
                    ) { Text("İptal") }

                    Button(
                        onClick = onSave,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary,
                            contentColor = colors.onPrimary,
                        )
                    ) { Text("Kaydet") }
                }
            }
        }
    }
}







