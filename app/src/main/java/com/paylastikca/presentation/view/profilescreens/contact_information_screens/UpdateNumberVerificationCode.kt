package com.paylastikca.presentation.view.profilescreens.contact_information_screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.paylastikca.presentation.view.common_ui.components.verification.core.VerificationCodeDialog

@Composable
fun UpdateNumberVerificationCode(
    phone: String,
    code: String,
    secondsLeft: Int,
    errorMessage: String?,
    onCodeChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onResend: () -> Unit
) {
    val pretty = remember(phone) { formatTrPhonePretty(phone) }

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
