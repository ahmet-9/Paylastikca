package com.paylastikca.presentation.view.profilescreens.privacy_security_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Sms
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.paylastikca.presentation.view.common_ui.components.layout.FixedPage
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.verification.core.CountdownEffect
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.mail_verification.MailVerificationCode
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.number_verification.NumberVerificationCode
import com.paylastikca.ui.UiDimens

private enum class DeleteVerifyMethod { SMS, EMAIL }

private sealed interface DeleteAccountModal {
    data object None : DeleteAccountModal

    data class ChooseMethod(
        val errorMessage: String? = null
    ) : DeleteAccountModal

    data class VerifyMail(
        val email: String,
        val code: String = "",
        val secondsLeft: Int = 59,
        val errorMessage: String? = null
    ) : DeleteAccountModal

    data class VerifySms(
        val phone: String,
        val code: String = "",
        val secondsLeft: Int = 59,
        val errorMessage: String? = null
    ) : DeleteAccountModal
}

@Composable
fun DeleteAccount(
    onBack: () -> Unit,
    registeredEmail: String = "ornek@example.com",
    registeredPhone: String = "+905551234567",
    onAccountDeleted: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()

    val topSpace = if (adaptive.isTablet) UiDimens.xl + UiDimens.xl + UiDimens.xl + UiDimens.xl + UiDimens.xl + UiDimens.xl + UiDimens.l else UiDimens.xl + UiDimens.xl + UiDimens.xl + UiDimens.xl
    val cardMaxWidth = adaptive.maxContentWidth ?: 380.dp
    val circleSize = if (adaptive.isTablet) 154.dp else 94.dp
    val circleBorder = if (adaptive.isTablet) 3.dp else 3.dp

    val titleSize = if (adaptive.isTablet) 52.sp else 25.sp
    val bodySize = if (adaptive.isTablet) 40.sp else 18.sp
    val confirmSize = if (adaptive.isTablet) 48.sp else 23.sp

    val titleLine = if (adaptive.isTablet) 36.sp else 24.sp
    val bodyLine = if (adaptive.isTablet) 45.sp else 25.sp
    val confirmLine = if (adaptive.isTablet) 40.sp else 25.sp

    val buttonTextSize = if (adaptive.isTablet) 45.sp else 28.sp
    val deleteTextSize = if (adaptive.isTablet) 45.sp else 26.sp

    val chooseMethodHeigh = if (adaptive.isTablet) 52.dp else 18.dp

    val buttonHeigh = if (adaptive.isTablet) UiDimens.xl + UiDimens.xl+ UiDimens.xl else UiDimens.xl + UiDimens.l + UiDimens.l


    val actionGap = if (adaptive.isTablet) UiDimens.xl + UiDimens.xl + UiDimens.xl + UiDimens.xl + UiDimens.xl + UiDimens.xl else UiDimens.xl + UiDimens.xl + UiDimens.xl
    val actionsTopSpace = if (adaptive.isTablet) UiDimens.xl + UiDimens.xl + UiDimens.xl + UiDimens.xl else UiDimens.xl + UiDimens.xl

    var selectedMethod by rememberSaveable { mutableStateOf(DeleteVerifyMethod.SMS) }
    var modal by remember { mutableStateOf<DeleteAccountModal>(DeleteAccountModal.None) }

    val isVerifyActive = modal is DeleteAccountModal.VerifyMail || modal is DeleteAccountModal.VerifySms
    val secondsLeft = when (val m = modal) {
        is DeleteAccountModal.VerifyMail -> m.secondsLeft
        is DeleteAccountModal.VerifySms -> m.secondsLeft
        else -> 0
    }

    CountdownEffect(
        isActive = isVerifyActive,
        secondsLeft = secondsLeft,
        onTick = { newS ->
            modal = when (val m = modal) {
                is DeleteAccountModal.VerifyMail -> m.copy(secondsLeft = newS)
                is DeleteAccountModal.VerifySms -> m.copy(secondsLeft = newS)
                else -> m
            }
        },
        onTimeout = {
            modal = when (val m = modal) {
                is DeleteAccountModal.VerifyMail ->
                    if (m.code.length < 6 && m.errorMessage.isNullOrBlank())
                        m.copy(errorMessage = "Tanımlanan sürede kodu girmediniz.")
                    else m

                is DeleteAccountModal.VerifySms ->
                    if (m.code.length < 6 && m.errorMessage.isNullOrBlank())
                        m.copy(errorMessage = "Tanımlanan sürede kodu girmediniz.")
                    else m

                else -> m
            }
        }
    )

    PageScaffold(
        containerColor = colors.background,
        topBar = {
            BackButton(
                title = "Hesabı sil",
                modifier = Modifier.padding(horizontal = UiDimens.l),
                onBackClick = onBack
            )
        }
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {
            Spacer(Modifier.height(topSpace))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = cardMaxWidth)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(UiDimens.rM),
                color = colors.error.copy(alpha = 0.45f),
                border = BorderStroke(2.dp, colors.error),
                shadowElevation = 0.dp,
                tonalElevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = UiDimens.l, vertical = UiDimens.l),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(circleSize),
                        contentAlignment = Alignment.Center,

                    ) {
                        Surface(
                            modifier = Modifier.size(circleSize),
                            shape = CircleShape,
                            color = colors.error.copy(alpha = 0f),
                            border = BorderStroke(circleBorder, colors.error)
                        ) {}

                        Text(
                            text = "i",
                            color = colors.error,
                            fontSize = if (adaptive.isTablet) 104.sp else 60.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    Spacer(Modifier.height(UiDimens.m))

                    Text(
                        text = "Hesabınızı silmek üzeresiniz !",
                        color = colors.onError,
                        fontSize = titleSize,
                        lineHeight = titleLine,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(UiDimens.s))

                    Text(
                        text = "Hesabınızı sildiğinizde, tüm profil bilgileriniz,\n" +
                                "geçmiş işlemleriniz ve kazandığınız ödüller kalıcı olarak\n" +
                                "silinecektir. Bu işlem geri alınamaz.",
                        color = colors.onError,
                        fontSize = bodySize,
                        lineHeight = bodyLine,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(UiDimens.m))

                    Text(
                        text = "Devam etmek istediğinize\nemin misiniz?",
                        color = colors.onError,
                        fontSize = confirmSize,
                        lineHeight = confirmLine,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(Modifier.height(actionsTopSpace))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = cardMaxWidth)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .height(buttonHeigh),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = colors.onPrimary
                    ),
                    contentPadding = PaddingValues(horizontal = 26.dp, vertical = 0.dp),
                ) {
                    Text(
                        text = "Vazgeç",
                        fontSize = buttonTextSize,
                        fontWeight = FontWeight.Black
                    )
                }

                Spacer(Modifier.width(actionGap))

                TextButton(
                    onClick = { modal = DeleteAccountModal.ChooseMethod() },
                    contentPadding = PaddingValues(horizontal = UiDimens.s, vertical = 0.dp)
                ) {
                    Text(
                        text = "Sil",
                        color = colors.error,
                        fontSize = deleteTextSize,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            // ---- Modal ----
            when (val m = modal) {
                DeleteAccountModal.None -> Unit

                is DeleteAccountModal.ChooseMethod -> {
                    Dialog(
                        onDismissRequest = { },
                        properties = DialogProperties(
                            dismissOnBackPress = false,
                            dismissOnClickOutside = false,
                            usePlatformDefaultWidth = false
                        )
                    ) {
                        Surface(
                            color = colors.surface,
                            shadowElevation = 10.dp,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.padding(horizontal = 22.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 18.dp, vertical = chooseMethodHeigh),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Doğrulama yöntemi seç",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colors.onSurface,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(Modifier.height(10.dp))

                                Text(
                                    text = "Hesabı silmek için SMS veya e-posta ile doğrulama yapmalısınız.",
                                    fontSize = 13.sp,
                                    lineHeight = 16.sp,
                                    color = colors.onSurfaceVariant,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(Modifier.height(14.dp))

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    MethodRow(
                                        title = "SMS",
                                        selected = selectedMethod == DeleteVerifyMethod.SMS,
                                        leadingIcon = { Icon(Icons.Outlined.Sms, contentDescription = null) },
                                        onClick = { selectedMethod = DeleteVerifyMethod.SMS }
                                    )
                                    MethodRow(
                                        title = "E-mail",
                                        selected = selectedMethod == DeleteVerifyMethod.EMAIL,
                                        leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                                        onClick = { selectedMethod = DeleteVerifyMethod.EMAIL }
                                    )
                                }

                                Spacer(Modifier.height(16.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    OutlinedButton(
                                        onClick = { modal = DeleteAccountModal.None },
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(48.dp),
                                        border = BorderStroke(1.dp, colors.primary),
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            contentColor = colors.primary
                                        )
                                    ) {
                                        Text("İptal", fontWeight = FontWeight.Bold)
                                    }

                                    Button(
                                        onClick = {
                                            val email = registeredEmail.trim()
                                            val phone = registeredPhone.trim()

                                            modal = when (selectedMethod) {
                                                DeleteVerifyMethod.EMAIL -> {
                                                    if (email.isBlank()) {
                                                        DeleteAccountModal.ChooseMethod(errorMessage = "Kayıtlı e-posta bulunamadı.")
                                                    } else {
                                                        DeleteAccountModal.VerifyMail(email = email, secondsLeft = 59)
                                                    }
                                                }

                                                DeleteVerifyMethod.SMS -> {
                                                    if (phone.isBlank()) {
                                                        DeleteAccountModal.ChooseMethod(errorMessage = "Kayıtlı telefon numarası bulunamadı.")
                                                    } else {
                                                        DeleteAccountModal.VerifySms(phone = phone, secondsLeft = 59)
                                                    }
                                                }
                                            }
                                        },
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(48.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = colors.primary,
                                            contentColor = colors.onPrimary
                                        )
                                    ) {
                                        Text("Devam", fontWeight = FontWeight.Bold)
                                    }
                                }

                                if (!m.errorMessage.isNullOrBlank()) {
                                    Spacer(Modifier.height(10.dp))
                                    Text(
                                        text = m.errorMessage,
                                        color = colors.error,
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }

                is DeleteAccountModal.VerifyMail -> {
                    MailVerificationCode(
                        email = m.email,
                        code = m.code,
                        secondsLeft = m.secondsLeft,
                        errorMessage = m.errorMessage,
                        onCodeChange = { input ->
                            val filtered = input.filter(Char::isDigit).take(6)
                            modal = m.copy(code = filtered, errorMessage = null)
                        },
                        onDismiss = { modal = DeleteAccountModal.None },
                        onConfirm = {
                            if (m.code != "123456") {
                                modal = m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin.")
                            } else {
                                modal = DeleteAccountModal.None
                                onAccountDeleted()
                            }
                        },
                        onResend = { modal = m.copy(code = "", errorMessage = null, secondsLeft = 59) }
                    )
                }

                is DeleteAccountModal.VerifySms -> {
                    NumberVerificationCode(
                        phone = m.phone,
                        code = m.code,
                        secondsLeft = m.secondsLeft,
                        errorMessage = m.errorMessage,
                        onCodeChange = { input ->
                            val filtered = input.filter(Char::isDigit).take(6)
                            modal = m.copy(code = filtered, errorMessage = null)
                        },
                        onDismiss = { modal = DeleteAccountModal.None },
                        onConfirm = {
                            if (m.code != "123456") {
                                modal = m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin.")
                            } else {
                                modal = DeleteAccountModal.None
                                onAccountDeleted()
                            }
                        },
                        onResend = { modal = m.copy(code = "", errorMessage = null, secondsLeft = 59) }
                    )
                }
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
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon()
            Spacer(Modifier.size(14.dp))

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
