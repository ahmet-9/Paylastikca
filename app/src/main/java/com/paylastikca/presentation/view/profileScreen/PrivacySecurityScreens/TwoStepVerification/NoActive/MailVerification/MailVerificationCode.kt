package com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.TwoStepVerification.NoActive.MailVerification

import androidx.compose.runtime.Composable
import com.paylastikca.presentation.view.commonUI.components.verification.core.VerificationCodeDialog

@Composable
fun MailVerificationCode(
    email: String,
    code: String,
    secondsLeft: Int,
    errorMessage: String?,
    onCodeChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onResend: () -> Unit
) {
    val safeEmail = email.trim().let { e ->
        if (e.length <= 28) e else e.take(12) + "…" + e.takeLast(12)
    }

    VerificationCodeDialog(
        title = "E-mail Doğrulaması\nBekleniyor",
        boldText = safeEmail,
        messageAfterBold = " adresine doğrulama kodu gönderildi. Lütfen kodu giriniz.",
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
