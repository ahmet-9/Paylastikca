package com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.number_verification

import androidx.compose.runtime.Composable
import com.paylastikca.presentation.view.common_ui.components.verification.core.VerificationCodeDialog
import com.paylastikca.presentation.view.profilescreens.contact_information_screens.formatTrPhonePretty

@Composable
fun NumberVerificationCode(
    phone: String,
    code: String,
    secondsLeft: Int,
    errorMessage: String?,
    onCodeChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onResend: () -> Unit
) {
    val pretty = formatTrPhonePretty(phone)

    VerificationCodeDialog(
        title = "Telefon Doğrulaması\nBekleniyor",
        boldText = pretty,
        messageAfterBold = " numarasına doğrulama kodu SMS olarak gönderildi. Lütfen kodu giriniz.",
        code = code,
        secondsLeft = secondsLeft,
        errorMessage = errorMessage,
        onCodeChange = onCodeChange,
        onDismiss = onDismiss,
        onConfirm = onConfirm,
        onResend = onResend,
        codeLength = 6
    )
}
