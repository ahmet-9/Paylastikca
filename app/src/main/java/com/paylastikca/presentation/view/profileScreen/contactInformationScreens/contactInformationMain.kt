package com.paylastikca.presentation.view.profileScreen.contactInformationScreens


import androidx.compose.material3.ExperimentalMaterial3Api


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private sealed interface ContactModal {
    data object None : ContactModal
    data class UpdateEmail(val currentEmail: String, val newEmail: String = "") : ContactModal
    data class VerifyEmail(
        val email: String,
        val code: String = "",
        val secondsLeft: Int = 59,
        val errorMessage: String? = null
    ) : ContactModal
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun contactInformationMain(
    onBack: () -> Unit = {}
) {

    val email = remember { mutableStateOf("ornek@example.com") }
    val phone = remember { mutableStateOf("+90 555 123 45 67") }
    val birthDate = remember { mutableStateOf("15 Mart 1990") }

    var modal by remember { mutableStateOf<ContactModal>(ContactModal.None) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("İletişim bilgileri") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("←") }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            InfoRow(
                title = "E-posta",
                value = email.value,
                actionText = "Düzenle",
                onAction = { modal = ContactModal.UpdateEmail(currentEmail = email.value) }
            )

            Divider()

            InfoRow(
                title = "Telefon Numarası",
                value = phone.value,
                actionText = "Düzenle",
                onAction = {  }
            )

            Divider()

            InfoRow(
                title = "Doğum Tarihi",
                value = birthDate.value,
                actionText = "Düzenle",
                onAction = {  }
            )
        }


        LaunchedEffect(modal) {
            val m = modal
            if (m is ContactModal.VerifyEmail) {
                while (true) {
                    val current = modal
                    if (current !is ContactModal.VerifyEmail) break

                    if (current.secondsLeft <= 0) {
                        if (current.code.length < 6 && current.errorMessage.isNullOrBlank()) {
                            modal = current.copy(errorMessage = "Tanımlanan sürede kodu girmediniz.")
                        }
                        break
                    }

                    delay(1000)
                    modal = current.copy(secondsLeft = current.secondsLeft - 1)
                }
            }
        }



        when (val m = modal) {
            ContactModal.None -> Unit

            is ContactModal.UpdateEmail -> {
                updateEmail(
                    newEmail = m.newEmail,
                    onNewEmailChange = { modal = m.copy(newEmail = it) },
                    onDismiss = { modal = ContactModal.None },
                    onSave = {

                        val trimmed = m.newEmail.trim()
                        if (trimmed.isBlank()) return@updateEmail
                        modal = ContactModal.VerifyEmail(email = trimmed, secondsLeft = 59)
                    }
                )
            }

            is ContactModal.VerifyEmail -> {
                updateEmailVerificationCode(
                    email = m.email,
                    code = m.code,
                    secondsLeft = m.secondsLeft,
                    errorMessage = m.errorMessage,
                    onCodeChange = { input ->  val filtered = input.filter(Char::isDigit).take(6)
                        modal = m.copy(code = filtered, errorMessage = null) },
                    onDismiss = { modal = ContactModal.None },
                    onConfirm = {

                        if (m.code != "123456") {
                            modal = m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin.")
                        } else {
                            email.value = m.email
                            modal = ContactModal.None
                        }
                    },
                    onResend = {

                        modal = m.copy(errorMessage = null, secondsLeft = 59, code = "")
                    }
                )
            }
        }
    }
}

@Composable
private fun InfoRow(
    title: String,
    value: String,
    actionText: String,
    onAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(title, style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.height(4.dp))
            Text(value, style = MaterialTheme.typography.bodyMedium)
        }
        Text(
            text = actionText,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(onClick = onAction)
        )
    }
}
