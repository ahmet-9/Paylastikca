package com.paylastikca.presentation.view.profileScreen.contactInformationScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paylastikca.presentation.view.commonUI.components.navigation.PageHeader
import com.paylastikca.presentation.view.commonUI.components.verification.core.CountdownEffect


import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

private fun Dp.s(scale: Float): Dp = (this.value * scale).dp
private fun TextUnit.s(scale: Float): TextUnit = (this.value * scale).sp

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

    data class UpdateBirthDate(val currentBirthDate: String) : ContactModal
}


private enum class ModalType { NONE, UPDATE_EMAIL, VERIFY_EMAIL, UPDATE_PHONE, VERIFY_PHONE, UPDATE_BIRTHDATE }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun contactInformationMain(
    onBack: () -> Unit = {}
) {

    val scale = 1.20f

    val colors = MaterialTheme.colorScheme

    var email by rememberSaveable { mutableStateOf("ornek@example.com") }
    var phone by rememberSaveable { mutableStateOf("+90 555 123 45 67") }
    var birthDate by rememberSaveable { mutableStateOf("15 Mart 1990") }

    // --- Saveable modal state ---
    var modalType by rememberSaveable { mutableStateOf(ModalType.NONE) }
    var modalA by rememberSaveable { mutableStateOf("") }
    var modalB by rememberSaveable { mutableStateOf("") }
    var modalSeconds by rememberSaveable { mutableIntStateOf(59) }
    var modalError by rememberSaveable { mutableStateOf<String?>(null) }

    fun toModal(): ContactModal = when (modalType) {
        ModalType.NONE -> ContactModal.None
        ModalType.UPDATE_EMAIL -> ContactModal.UpdateEmail(currentEmail = modalA, newEmail = modalB)
        ModalType.VERIFY_EMAIL -> ContactModal.VerifyEmail(email = modalA, code = modalB, secondsLeft = modalSeconds, errorMessage = modalError)
        ModalType.UPDATE_PHONE -> ContactModal.UpdatePhone(currentPhone = modalA, newPhone = modalB, errorMessage = modalError)
        ModalType.VERIFY_PHONE -> ContactModal.VerifyPhone(phone = modalA, code = modalB, secondsLeft = modalSeconds, errorMessage = modalError)
        ModalType.UPDATE_BIRTHDATE -> ContactModal.UpdateBirthDate(currentBirthDate = modalA)
    }

    fun setModal(m: ContactModal) {
        when (m) {
            ContactModal.None -> {
                modalType = ModalType.NONE
                modalA = ""
                modalB = ""
                modalSeconds = 59
                modalError = null
            }
            is ContactModal.UpdateEmail -> {
                modalType = ModalType.UPDATE_EMAIL
                modalA = m.currentEmail
                modalB = m.newEmail
                modalSeconds = 59
                modalError = null
            }
            is ContactModal.VerifyEmail -> {
                modalType = ModalType.VERIFY_EMAIL
                modalA = m.email
                modalB = m.code
                modalSeconds = m.secondsLeft
                modalError = m.errorMessage
            }
            is ContactModal.UpdatePhone -> {
                modalType = ModalType.UPDATE_PHONE
                modalA = m.currentPhone
                modalB = m.newPhone
                modalSeconds = 59
                modalError = m.errorMessage
            }
            is ContactModal.VerifyPhone -> {
                modalType = ModalType.VERIFY_PHONE
                modalA = m.phone
                modalB = m.code
                modalSeconds = m.secondsLeft
                modalError = m.errorMessage
            }
            is ContactModal.UpdateBirthDate -> {
                modalType = ModalType.UPDATE_BIRTHDATE
                modalA = m.currentBirthDate
                modalB = ""
                modalSeconds = 59
                modalError = null
            }
        }
    }

    val modal = toModal()

    // --- Countdown ---
    val isVerifyActive = modal is ContactModal.VerifyEmail || modal is ContactModal.VerifyPhone
    val secondsLeft = when (modal) {
        is ContactModal.VerifyEmail -> modal.secondsLeft
        is ContactModal.VerifyPhone -> modal.secondsLeft
        else -> 0
    }

    CountdownEffect(
        isActive = isVerifyActive,
        secondsLeft = secondsLeft,
        onTick = { newS ->
            when (val m = toModal()) {
                is ContactModal.VerifyEmail -> setModal(m.copy(secondsLeft = newS))
                is ContactModal.VerifyPhone -> setModal(m.copy(secondsLeft = newS))
                else -> Unit
            }
        },
        onTimeout = {
            when (val m = toModal()) {
                is ContactModal.VerifyEmail -> {
                    if (m.code.length < 6 && m.errorMessage.isNullOrBlank()) {
                        setModal(m.copy(errorMessage = "Tanımlanan sürede kodu girmediniz."))
                    }
                }
                is ContactModal.VerifyPhone -> {
                    if (m.code.length < 6 && m.errorMessage.isNullOrBlank()) {
                        setModal(m.copy(errorMessage = "Tanımlanan sürede kodu girmediniz."))
                    }
                }
                else -> Unit
            }
        }
    )

    Scaffold(
        containerColor = colors.background,
        contentColor = colors.onBackground,
        topBar = {
            PageHeader(
                modifier = Modifier.padding(top = 25.dp.s(scale), start = 7.dp.s(scale)),
                title = "İletişim bilgileri",
                onBackClick = onBack
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp.s(scale)),
            verticalArrangement = Arrangement.spacedBy(18.dp.s(scale))
        ) {
            InfoRow(
                title = "E-posta",
                value = email,
                actionText = "Düzenle",
                onAction = { setModal(ContactModal.UpdateEmail(currentEmail = email)) },
                scale = scale
            )

            Divider(color = colors.outlineVariant)

            InfoRow(
                title = "Telefon Numarası",
                value = phone,
                actionText = "Düzenle",
                onAction = { setModal(ContactModal.UpdatePhone(currentPhone = phone)) },
                scale = scale
            )

            Divider()

            InfoRow(
                title = "Doğum Tarihi",
                value = birthDate,
                actionText = "Düzenle",
                onAction = { setModal(ContactModal.UpdateBirthDate(currentBirthDate = birthDate)) },
                scale = scale
            )
        }

        when (val m = toModal()) {
            ContactModal.None -> Unit

            is ContactModal.UpdateEmail -> {
                updateEmail(
                    newEmail = m.newEmail,
                    onNewEmailChange = { setModal(m.copy(newEmail = it)) },
                    onDismiss = { setModal(ContactModal.None) },
                    onSave = {
                        val trimmed = m.newEmail.trim()
                        if (trimmed.isBlank()) return@updateEmail
                        setModal(ContactModal.VerifyEmail(email = trimmed, secondsLeft = 59))
                    }
                )
            }

            is ContactModal.VerifyEmail -> {
                updateEmailVerificationCode(
                    email = m.email,
                    code = m.code,
                    secondsLeft = m.secondsLeft,
                    errorMessage = m.errorMessage,
                    onCodeChange = { input ->
                        val filtered = input.filter(Char::isDigit).take(6)
                        setModal(m.copy(code = filtered, errorMessage = null))
                    },
                    onDismiss = { setModal(ContactModal.None) },
                    onConfirm = {
                        if (m.code != "123456") {
                            setModal(m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin."))
                        } else {
                            email = m.email
                            setModal(ContactModal.None)
                        }
                    },
                    onResend = { setModal(m.copy(errorMessage = null, secondsLeft = 59, code = "")) }
                )
            }

            is ContactModal.UpdatePhone -> {
                updateNumber(
                    currentPhone = m.currentPhone,
                    newPhone = m.newPhone,
                    errorMessage = m.errorMessage,
                    onNewPhoneChange = { input -> setModal(m.copy(newPhone = input, errorMessage = null)) },
                    onDismiss = { setModal(ContactModal.None) },
                    onSave = {
                        val raw = m.newPhone.trim()
                        if (raw.isBlank()) {
                            setModal(m.copy(errorMessage = "Telefon numarası boş olamaz."))
                            return@updateNumber
                        }
                        val normalized = normalizeTrPhone(raw)
                        if (normalized == null) {
                            setModal(m.copy(errorMessage = "Geçersiz numara."))
                            return@updateNumber
                        }
                        setModal(ContactModal.VerifyPhone(phone = normalized, secondsLeft = 59))
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
                        setModal(m.copy(code = filtered, errorMessage = null))
                    },
                    onDismiss = { setModal(ContactModal.None) },
                    onConfirm = {
                        if (m.code != "123456") {
                            setModal(m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin."))
                        } else {
                            phone = formatTrPhonePretty(m.phone)
                            setModal(ContactModal.None)
                        }
                    },
                    onResend = { setModal(m.copy(errorMessage = null, secondsLeft = 59, code = "")) }
                )
            }

            is ContactModal.UpdateBirthDate -> {
                dateOfBirth(
                    currentBirthDate = m.currentBirthDate,
                    onDismiss = { setModal(ContactModal.None) },
                    onSave = { formatted ->
                        birthDate = formatted
                        setModal(ContactModal.None)
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
    onAction: () -> Unit,
    scale: Float
) {
    val colors = MaterialTheme.colorScheme
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(title, style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp.s(scale)), color = colors.onSurface)
            Spacer(Modifier.height(4.dp.s(scale)))
            Text(value, style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp.s(scale), color = colors.onSurfaceVariant))
        }
        Text(
            text = actionText,
            color = colors.primary,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 14.sp.s(scale)
            ),
            modifier = Modifier.clickable(onClick = onAction)
        )
    }
}

private fun normalizeTrPhone(input: String): String? {
    val digits = input.filter(Char::isDigit)
    return when {
        digits.length == 11 && digits.startsWith("05") -> "+90" + digits.drop(1)
        digits.length == 10 && digits.startsWith("5") -> "+90$digits"
        digits.length == 12 && digits.startsWith("90") && digits.getOrNull(2) == '5' -> "+$digits"
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





