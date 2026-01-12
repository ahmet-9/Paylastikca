package com.paylastikca.presentation.view.profileScreen.contactInformationScreens

import androidx.compose.runtime.Composable
import com.paylastikca.presentation.view.commonUI.components.verification.core.VerificationCodeDialog

@Composable
fun updateEmailVerificationCode(
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
