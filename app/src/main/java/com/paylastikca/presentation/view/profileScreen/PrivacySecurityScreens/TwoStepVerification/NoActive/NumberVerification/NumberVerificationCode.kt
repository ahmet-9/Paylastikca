package com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.TwoStepVerification.NoActive.NumberVerification

import androidx.compose.runtime.Composable
import com.paylastikca.presentation.view.commonUI.components.verification.core.VerificationCodeDialog
import com.paylastikca.presentation.view.profileScreen.contactInformationScreens.formatTrPhonePretty

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
