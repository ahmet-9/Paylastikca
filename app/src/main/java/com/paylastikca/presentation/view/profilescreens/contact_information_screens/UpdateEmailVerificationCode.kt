package com.paylastikca.presentation.view.profilescreens.contact_information_screens

import androidx.compose.runtime.Composable
import com.paylastikca.presentation.view.common_ui.components.verification.core.VerificationCodeDialog

@Composable
fun UpdateEmailVerificationCode(
    email: String,
    code: String,
    secondsLeft: Int,
    errorMessage: String?,
    onCodeChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onResend: () -> Unit
) {
    VerificationCodeDialog(
        title = "E-posta Doğrulaması\nBekleniyor",
        boldText = email,
        messageAfterBold = " adresine e-postanı doğrulamak için gerekli kod gönderildi. Lütfen kodu giriniz.",
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
