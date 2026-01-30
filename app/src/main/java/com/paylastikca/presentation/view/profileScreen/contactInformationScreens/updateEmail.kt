package com.paylastikca.presentation.view.profileScreen.contactInformationScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun updateEmail(
    newEmail: String,
    onNewEmailChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Dialog(onDismissRequest = onDismiss) {

        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val isTablet = maxWidth >= 600.dp
            val isSmallPhone = maxWidth < 380.dp // Pixel 3 gibi

            val widthFraction = when {
                isTablet -> 0.70f
                isSmallPhone -> 0.92f
                else -> 0.86f
            }


            val maxDialogWidth = if (isTablet) 520.dp else 420.dp

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = colors.surface),
                modifier = Modifier
                    .fillMaxWidth(widthFraction)
                    .widthIn(max = maxDialogWidth)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "E-posta Güncelle",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = colors.onSurface
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = newEmail,
                        onValueChange = onNewEmailChange,
                        label = { Text("Yeni E-posta") },
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

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Kaydet'e bastıktan sonra hesabınızın güvenliği için doğrulama e-postası gönderilecek.",
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colors.surface,
                                contentColor = colors.primary
                            ),
                            border = BorderStroke(1.dp, colors.primary)
                        ) { Text("İptal") }

                        Button(
                            onClick = onSave,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colors.primary,
                                contentColor = colors.onPrimary
                            )
                        ) { Text("Kaydet") }
                    }
                }
            }
        }
    }
}
