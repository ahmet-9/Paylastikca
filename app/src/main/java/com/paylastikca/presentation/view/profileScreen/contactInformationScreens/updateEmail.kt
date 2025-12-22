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
fun updateEmail(
    newEmail: String,
    onNewEmailChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Column(Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("E-posta Güncelle", style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp


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
                        focusedBorderColor = Color(0xFF969595),
                        unfocusedBorderColor = Color(0xFFD0D0D0),
                        cursorColor = Color(0xFFFF7200),
                        focusedLabelColor = Color(0xFFFF7200)
                    )
                )

                Spacer(Modifier.height(8.dp))
                Text(
                    "Kaydet'e bastıktan sonra hesabınızın güvenliği için doğrulama e-postası gönderilecek.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(16.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFFFF7200),

                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color(0xFFFF7200)
                        )

                    ) { Text("İptal") }

                    Button(
                        onClick = onSave,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF7200),
                            contentColor = Color.White,

                            )

                    ) { Text("Kaydet") }
                }
            }
        }
    }
}
