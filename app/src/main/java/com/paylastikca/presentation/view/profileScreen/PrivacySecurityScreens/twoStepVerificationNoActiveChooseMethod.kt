package com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens

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
import com.paylastikca.presentation.view.commonUI.components.PageHeader
import com.paylastikca.presentation.view.commonUI.components.verification.core.CountdownEffect

private enum class TwoStepMethod { SMS, EMAIL }

private sealed interface TwoStepModal {
    data object None : TwoStepModal

    // EMAIL
    data class MailSend(
        val email: String = "",
        val errorMessage: String? = null
    ) : TwoStepModal

    data class MailVerify(
        val email: String,
        val code: String = "",
        val secondsLeft: Int = 59,
        val errorMessage: String? = null
    ) : TwoStepModal

    // SMS
    data class NumberSend(
        val phone: String = "",
        val errorMessage: String? = null
    ) : TwoStepModal

    data class NumberVerify(
        val phone: String,
        val code: String = "",
        val secondsLeft: Int = 59,
        val errorMessage: String? = null
    ) : TwoStepModal
}

@Composable
fun twoStepVerificationNoActiveChooseMethod(
    onBack: () -> Unit = {},

    onVerifiedSuccess: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme

    var selected by remember { mutableStateOf(TwoStepMethod.SMS) }
    var modal by remember { mutableStateOf<TwoStepModal>(TwoStepModal.None) }


    val isVerifyActive = modal is TwoStepModal.MailVerify || modal is TwoStepModal.NumberVerify
    val secondsLeft = when (val m = modal) {
        is TwoStepModal.MailVerify -> m.secondsLeft
        is TwoStepModal.NumberVerify -> m.secondsLeft
        else -> 0
    }



    CountdownEffect(
        isActive = isVerifyActive,
        secondsLeft = secondsLeft,
        onTick = { newS ->
            modal = when (val m = modal) {
                is TwoStepModal.MailVerify -> m.copy(secondsLeft = newS)
                is TwoStepModal.NumberVerify -> m.copy(secondsLeft = newS)
                else -> m
            }
        },
        onTimeout = {
            modal = when (val m = modal) {
                is TwoStepModal.MailVerify ->
                    if (m.code.length < 6 && m.errorMessage.isNullOrBlank())
                        m.copy(errorMessage = "Tanımlanan sürede kodu girmediniz.")
                    else m

                is TwoStepModal.NumberVerify ->
                    if (m.code.length < 6 && m.errorMessage.isNullOrBlank())
                        m.copy(errorMessage = "Tanımlanan sürede kodu girmediniz.")
                    else m

                else -> m
            }
        }
    )

    Scaffold(
        containerColor = colors.background,
        contentColor = colors.onBackground
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            PageHeader(
                modifier = Modifier.padding(top = 2.dp, start = 7.dp, end = 7.dp, bottom = 20.dp),
                title = "İki adımlı doğrulama",
                onBackClick = onBack
            )


            Spacer(Modifier.height(22.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    color = Color.Transparent,
                    border = BorderStroke(3.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "i",

                            fontSize = 28.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }

                Spacer(Modifier.height(6.dp))


                Text(
                    text = "Hesap güvenliğiniz için doğrulama gereklidir.\n" +
                            "Dilerseniz SMS veya e-posta yoluyla doğrulama\n"+" yapabilirsiniz.",
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
                    selected = selected == TwoStepMethod.SMS,
                    leadingIcon = { Icon(Icons.Outlined.Sms, contentDescription = null) },
                    onClick = { selected = TwoStepMethod.SMS }
                )

                MethodRow(
                    title = "E-mail",
                    selected = selected == TwoStepMethod.EMAIL,
                    leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                    onClick = { selected = TwoStepMethod.EMAIL }
                )
            }

            Spacer(Modifier.height(28.dp))

            Button(
                onClick = {
                    when (selected) {
                        TwoStepMethod.SMS -> modal = TwoStepModal.NumberSend()
                        TwoStepMethod.EMAIL -> modal = TwoStepModal.MailSend()
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

        when (val m = modal) {
            TwoStepModal.None -> Unit

            // EMAIL
            is TwoStepModal.MailSend -> {
                twoStepVerificationNoActiveChooseMethodMailSend(
                    email = m.email,
                    errorMessage = m.errorMessage,
                    onEmailChange = { modal = m.copy(email = it, errorMessage = null) },
                    onDismiss = { modal = TwoStepModal.None },
                    onSend = {
                        val trimmed = m.email.trim()
                        if (trimmed.isBlank()) {
                            modal = m.copy(errorMessage = "E-posta boş olamaz.")
                            return@twoStepVerificationNoActiveChooseMethodMailSend
                        }
                        if (!trimmed.contains("@") || !trimmed.contains(".")) {
                            modal = m.copy(errorMessage = "Geçersiz e-posta.")
                            return@twoStepVerificationNoActiveChooseMethodMailSend
                        }
                        modal = TwoStepModal.MailVerify(email = trimmed, secondsLeft = 59)
                    }
                )
            }

            is TwoStepModal.MailVerify -> {
                twoStepVerificationNoActiveChooseMethodMailSendVerificationCode(
                    email = m.email,
                    code = m.code,
                    secondsLeft = m.secondsLeft,
                    errorMessage = m.errorMessage,
                    onCodeChange = { input ->
                        val filtered = input.filter(Char::isDigit).take(6)
                        modal = m.copy(code = filtered, errorMessage = null)
                    },
                    onDismiss = { modal = TwoStepModal.None },
                    onConfirm = {
                        if (m.code != "123456") {
                            modal = m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin.")
                        } else {
                            modal = TwoStepModal.None
                            onVerifiedSuccess()
                        }
                    },
                    onResend = {
                        modal = m.copy(code = "", errorMessage = null, secondsLeft = 59)
                    }
                )
            }

            // SMS
            is TwoStepModal.NumberSend -> {
                twoStepVerificationNoActiveChooseMethodNumberSend(
                    phone = m.phone,
                    errorMessage = m.errorMessage,
                    onPhoneChange = { modal = m.copy(phone = it, errorMessage = null) },
                    onDismiss = { modal = TwoStepModal.None },
                    onSend = {
                        val raw = m.phone.trim()
                        if (raw.isBlank()) {
                            modal = m.copy(errorMessage = "Telefon numarası boş olamaz.")
                            return@twoStepVerificationNoActiveChooseMethodNumberSend
                        }


                        val normalized = normalizeTrPhone(raw)
                        if (normalized == null) {
                            modal = m.copy(errorMessage = "Geçersiz numara.")
                            return@twoStepVerificationNoActiveChooseMethodNumberSend
                        }

                        modal = TwoStepModal.NumberVerify(phone = normalized, secondsLeft = 59)
                    }
                )
            }

            is TwoStepModal.NumberVerify -> {
                twoStepVerificationNoActiveChooseMethodNumberSendVerificationCode(
                    phone = m.phone,
                    code = m.code,
                    secondsLeft = m.secondsLeft,
                    errorMessage = m.errorMessage,
                    onCodeChange = { input ->
                        val filtered = input.filter(Char::isDigit).take(6)
                        modal = m.copy(code = filtered, errorMessage = null)
                    },
                    onDismiss = { modal = TwoStepModal.None },
                    onConfirm = {
                        if (m.code != "123456") {
                            modal = m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin.")
                        } else {
                            modal = TwoStepModal.None
                            onVerifiedSuccess()
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
