package com.paylastikca.presentation.view.profileScreen.contactInformationScreens


import androidx.compose.material3.ExperimentalMaterial3Api


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paylastikca.presentation.view.commonUI.components.PageHeader
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

    data class UpdatePhone(
        val currentPhone: String,
        val newPhone: String = "",
        val errorMessage: String? = null
    ) : ContactModal

    data class VerifyPhone(
        val phone: String,
        val code: String = "",
        val secondsLeft: Int = 59,
        val errorMessage: String? = null
    ) : ContactModal


    data class UpdateBirthDate(
        val currentBirthDate: String,
        val newBirthDate: String = "",
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

                PageHeader(
                    modifier = Modifier.padding(top = 25.dp, start = 7.dp),
                    title = "İletişim bilgileri",
                    onBackClick = onBack
                )


        }
    )
    { padding ->

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
                onAction = { modal = ContactModal.UpdatePhone(currentPhone = phone.value) }
            )

            Divider()

            InfoRow(
                title = "Doğum Tarihi",
                value = birthDate.value,
                actionText = "Düzenle",
                onAction = {    modal = ContactModal.UpdateBirthDate(currentBirthDate = birthDate.value)
                 }
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

        LaunchedEffect(modal) {
            while (true) {
                val current = modal

                val secondsLeft = when (current) {
                    is ContactModal.VerifyEmail -> current.secondsLeft
                    is ContactModal.VerifyPhone -> current.secondsLeft
                    else -> break
                }

                if (secondsLeft <= 0) {
                    when (current) {
                        is ContactModal.VerifyEmail -> {
                            if (current.code.length < 6 && current.errorMessage.isNullOrBlank()) {
                                modal = current.copy(
                                    errorMessage = "Tanımlanan sürede kodu girmediniz."
                                )
                            }
                        }
                        is ContactModal.VerifyPhone -> {
                            if (current.code.length < 6 && current.errorMessage.isNullOrBlank()) {
                                modal = current.copy(
                                    errorMessage = "Tanımlanan sürede kodu girmediniz."
                                )
                            }
                        }
                        else -> Unit
                    }
                    break
                }

                delay(1000)

                modal = when (current) {
                    is ContactModal.VerifyEmail ->
                        current.copy(secondsLeft = current.secondsLeft - 1)

                    is ContactModal.VerifyPhone ->
                        current.copy(secondsLeft = current.secondsLeft - 1)

                    else -> break
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

            is ContactModal.UpdatePhone -> {
                updateNumber(
                    currentPhone = m.currentPhone,
                    newPhone = m.newPhone,
                    errorMessage = m.errorMessage,
                    onNewPhoneChange = { input ->

                        modal = m.copy(newPhone = input, errorMessage = null) },
                    onDismiss = { modal = ContactModal.None },
                    onSave = {
                        val raw = m.newPhone.trim()


                        if (raw.isBlank()) {
                            modal = m.copy(errorMessage = "Telefon numarası boş olamaz.")
                            return@updateNumber
                        }


                        val normalized = normalizeTrPhone(raw)
                        if (normalized == null) {
                            modal = m.copy(errorMessage = "Geçersiz numara.")
                            return@updateNumber
                        }


                        modal = ContactModal.VerifyPhone(phone = normalized, secondsLeft = 59)
                    }
                )
            }

            is ContactModal.VerifyPhone -> {
                updateNumberVerificationCode(
                    phone = m.phone,
                    code = m.code,
                    secondsLeft = m.secondsLeft,
                    errorMessage = m.errorMessage,
                    onCodeChange = { input ->
                        val filtered = input.filter(Char::isDigit).take(6)
                        modal = m.copy(code = filtered, errorMessage = null)
                    },
                    onDismiss = { modal = ContactModal.None },
                    onConfirm = {

                        if (m.code != "123456") {
                            modal = m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin.")
                        } else {
                            phone.value = formatTrPhonePretty(m.phone)
                            modal = ContactModal.None
                        }
                    },
                    onResend = {

                        modal = m.copy(errorMessage = null, secondsLeft = 59, code = "")
                    }
                )
            }

            is ContactModal.UpdateBirthDate -> {
                dateOfBirth(
                    currentBirthDate = m.currentBirthDate,
                    onDismiss = { modal = ContactModal.None },
                    onSave = { formatted ->
                        birthDate.value = formatted
                        modal = ContactModal.None
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


private fun normalizeTrPhone(input: String): String? {
    val digits = input.filter(Char::isDigit)



    return when {
        digits.length == 11 && digits.startsWith("05") -> "+90" + digits.drop(1)
        digits.length == 10 && digits.startsWith("5") -> "+90$digits"
        digits.length == 12 && digits.startsWith("90") && digits[2] == '5' -> "+$digits"
        digits.length == 13 && digits.startsWith("905") -> "+$digits"
        else -> null
    }
}

 fun formatTrPhonePretty(normalized: String): String {

    val d = normalized.filter(Char::isDigit)
    if (d.length < 12) return normalized

    val country = d.take(2)
    val rest = d.drop(2)
    val a = rest.take(3)
    val b = rest.drop(3).take(3)
    val c = rest.drop(6).take(2)
    val e = rest.drop(8).take(2)

    return "+$country $a $b $c $e"
}
