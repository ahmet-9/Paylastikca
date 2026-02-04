package com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.active

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Sms
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.verification.core.CountdownEffect
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.mail_verification.MailVerificationCode
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.number_verification.NumberVerificationCode

private enum class RemoveMethod { SMS, EMAIL }

private sealed interface RemoveModal {
    data object None : RemoveModal

    data class MailVerify(
        val email: String,
        val code: String = "",
        val secondsLeft: Int = 59,
        val errorMessage: String? = null
    ) : RemoveModal

    data class NumberVerify(
        val phone: String,
        val code: String = "",
        val secondsLeft: Int = 59,
        val errorMessage: String? = null
    ) : RemoveModal
}



@Composable
fun VerificationRemoveChooseMethod(
    registeredEmail: String,
    registeredPhone: String,
    onBack: () -> Unit = {},
    onRemoveVerifiedSuccess: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme

    var selected by remember { mutableStateOf(RemoveMethod.SMS) }
    var modal by remember { mutableStateOf<RemoveModal>(RemoveModal.None) }

    val isVerifyActive = modal is RemoveModal.MailVerify || modal is RemoveModal.NumberVerify
    val secondsLeft = when (val m = modal) {
        is RemoveModal.MailVerify -> m.secondsLeft
        is RemoveModal.NumberVerify -> m.secondsLeft
        else -> 0
    }

    CountdownEffect(
        isActive = isVerifyActive,
        secondsLeft = secondsLeft,
        onTick = { newS ->
            modal = when (val m = modal) {
                is RemoveModal.MailVerify -> m.copy(secondsLeft = newS)
                is RemoveModal.NumberVerify -> m.copy(secondsLeft = newS)
                else -> m
            }
        },
        onTimeout = {
            modal = when (val m = modal) {
                is RemoveModal.MailVerify ->
                    if (m.code.length < 6 && m.errorMessage.isNullOrBlank())
                        m.copy(errorMessage = "Tanımlanan sürede kodu girmediniz.")
                    else m

                is RemoveModal.NumberVerify ->
                    if (m.code.length < 6 && m.errorMessage.isNullOrBlank())
                        m.copy(errorMessage = "Tanımlanan sürede kodu girmediniz.")
                    else m

                else -> m
            }
        }
    )

    Scaffold(
        containerColor = colors.background,
        contentColor = colors.onBackground,
        topBar = {
            BackButton(
                modifier = Modifier.padding(start = 7.dp, end = 7.dp),
                title = "Doğrulama",
                onBackClick = onBack
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Spacer(Modifier.height(18.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    color = Color.Transparent,
                    border = BorderStroke(3.dp, colors.outline)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "i",
                            fontSize = 28.sp,
                            color = colors.outline
                        )
                    }
                }

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "İki adımlı doğrulamayı kaldırmak için\nSMS veya e-posta ile doğrulama yapmanız gerekir.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.onSurfaceVariant,
                    fontSize = 14.sp
                )
            }

            Spacer(Modifier.height(22.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                MethodRow(
                    title = "SMS",
                    selected = selected == RemoveMethod.SMS,
                    leadingIcon = { Icon(Icons.Outlined.Sms, contentDescription = null) },
                    onClick = { selected = RemoveMethod.SMS }
                )

                MethodRow(
                    title = "E-mail",
                    selected = selected == RemoveMethod.EMAIL,
                    leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                    onClick = { selected = RemoveMethod.EMAIL }
                )
            }

            Spacer(Modifier.height(28.dp))

            Button(
                onClick = {
                    when (selected) {
                        RemoveMethod.SMS -> {
                            val phone = registeredPhone.trim()
                            if (phone.isBlank()) {

                                modal = RemoveModal.NumberVerify(
                                    phone = "",
                                    secondsLeft = 59,
                                    errorMessage = "Kayıtlı telefon numarası bulunamadı."
                                )
                            } else {
                                modal = RemoveModal.NumberVerify(phone = phone, secondsLeft = 59)
                            }
                        }

                        RemoveMethod.EMAIL -> {
                            val email = registeredEmail.trim()
                            if (email.isBlank()) {
                                modal = RemoveModal.MailVerify(
                                    email = "",
                                    secondsLeft = 59,
                                    errorMessage = "Kayıtlı e-posta bulunamadı."
                                )
                            } else {
                                modal = RemoveModal.MailVerify(email = email, secondsLeft = 59)
                            }
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 50.dp)
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary,
                    contentColor = colors.onPrimary
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text("Devam", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        // ---- OTP Dialog ----
        when (val m = modal) {
            RemoveModal.None -> Unit

            is RemoveModal.MailVerify -> {
                MailVerificationCode(
                    email = m.email,
                    code = m.code,
                    secondsLeft = m.secondsLeft,
                    errorMessage = m.errorMessage,
                    onCodeChange = { input ->
                        val filtered = input.filter(Char::isDigit).take(6)
                        modal = m.copy(code = filtered, errorMessage = null)
                    },
                    onDismiss = { modal = RemoveModal.None },
                    onConfirm = {
                        // TODO: API doğrulama -> kaldırma onayı
                        if (m.code != "123456") {
                            modal = m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin.")
                        } else {
                            modal = RemoveModal.None
                            onRemoveVerifiedSuccess()
                        }
                    },
                    onResend = {
                        modal = m.copy(code = "", errorMessage = null, secondsLeft = 59)
                    }
                )
            }

            is RemoveModal.NumberVerify -> {
                NumberVerificationCode(
                    phone = m.phone,
                    code = m.code,
                    secondsLeft = m.secondsLeft,
                    errorMessage = m.errorMessage,
                    onCodeChange = { input ->
                        val filtered = input.filter(Char::isDigit).take(6)
                        modal = m.copy(code = filtered, errorMessage = null)
                    },
                    onDismiss = { modal = RemoveModal.None },
                    onConfirm = {

                        if (m.code != "123456") {
                            modal = m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin.")
                        } else {
                            modal = RemoveModal.None
                            onRemoveVerifiedSuccess()
                        }
                    },
                    onResend = {
                        modal = m.copy(code = "", errorMessage = null, secondsLeft = 59)
                    }
                )
            }
        }
    }
}

@Composable
private fun MethodRow(
    title: String,
    selected: Boolean,
    leadingIcon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        color = colors.surface,
        border = BorderStroke(1.dp, colors.outlineVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon()
            Spacer(Modifier.width(14.dp))

            Text(
                text = title,
                fontSize = 18.sp,
                color = colors.onSurface
            )

            Spacer(Modifier.weight(1f))

            if (selected) {
                Surface(
                    shape = CircleShape,
                    color = colors.primary,
                    modifier = Modifier.size(28.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            tint = colors.onPrimary
                        )
                    }
                }
            } else {
                Surface(
                    shape = CircleShape,
                    color = colors.surface,
                    border = BorderStroke(1.5.dp, colors.outline),
                    modifier = Modifier.size(28.dp)
                ) {}
            }
        }
    }
}
