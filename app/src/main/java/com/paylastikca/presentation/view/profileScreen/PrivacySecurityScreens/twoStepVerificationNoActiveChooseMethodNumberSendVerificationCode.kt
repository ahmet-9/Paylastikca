package com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens

import androidx.compose.runtime.Composable
import com.paylastikca.presentation.view.commonUI.components.verification.core.VerificationCodeDialog

@Composable
fun twoStepVerificationNoActiveChooseMethodNumberSendVerificationCode(
    phone: String,
    code: String,
    secondsLeft: Int,
    errorMessage: String?,
    onCodeChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onResend: () -> Unit
) {
    VerificationCodeDialog(
        title = "Telefon Doğrulaması\nBekleniyor",
        boldText = phone,
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
