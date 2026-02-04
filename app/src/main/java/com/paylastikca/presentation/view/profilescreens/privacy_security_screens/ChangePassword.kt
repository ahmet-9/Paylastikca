package com.paylastikca.presentation.view.profilescreens.privacy_security_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Sms
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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

private enum class VerifyMethod { SMS, EMAIL }

private sealed interface ChangePassModal {
    data object None : ChangePassModal

    data class ChooseMethod(
        val errorMessage: String? = null
    ) : ChangePassModal

    data class VerifyMail(
        val email: String,
        val code: String = "",
        val secondsLeft: Int = 59,
        val errorMessage: String? = null
    ) : ChangePassModal

    data class VerifySms(
        val phone: String,
        val code: String = "",
        val secondsLeft: Int = 59,
        val errorMessage: String? = null
    ) : ChangePassModal
}

@Composable
fun ChangePassword(
    onBack: () -> Unit = {},
    registeredEmail: String = "ornek@example.com",
    registeredPhone: String = "+905551234567",
    onPasswordChanged: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()

    var currentPass by rememberSaveable { mutableStateOf("") }
    var newPass by rememberSaveable { mutableStateOf("") }
    var confirmPass by rememberSaveable { mutableStateOf("") }

    var showCurrent by rememberSaveable { mutableStateOf(false) }
    var showNew by rememberSaveable { mutableStateOf(false) }
    var showConfirm by rememberSaveable { mutableStateOf(false) }

    var currentTouched by rememberSaveable { mutableStateOf(false) }
    var newTouched by rememberSaveable { mutableStateOf(false) }
    var confirmTouched by rememberSaveable { mutableStateOf(false) }
    var submitAttempted by rememberSaveable { mutableStateOf(false) }

    val currentErrRaw = remember(currentPass) {
        if (currentPass.isBlank()) "Mevcut şifre boş olamaz." else null
    }
    val newErrRaw = remember(newPass) { validateNewPassword(newPass) }
    val confirmErrRaw = remember(newPass, confirmPass) {
        when {
            confirmPass.isBlank() -> "Yeni şifre tekrar boş olamaz."
            newPass != confirmPass -> "Şifreler eşleşmiyor."
            else -> null
        }
    }

    val showCurrentErr = submitAttempted || currentTouched
    val showNewErr = submitAttempted || newTouched
    val showConfirmErr = submitAttempted || confirmTouched

    val currentErr = if (showCurrentErr) currentErrRaw else null
    val newErr = if (showNewErr) newErrRaw else null
    val confirmErr = if (showConfirmErr) confirmErrRaw else null

    val isFormValid = currentErrRaw == null && newErrRaw == null && confirmErrRaw == null

    var selectedMethod by rememberSaveable { mutableStateOf(VerifyMethod.SMS) }
    var modal by remember { mutableStateOf<ChangePassModal>(ChangePassModal.None) }

    val isVerifyActive = modal is ChangePassModal.VerifyMail || modal is ChangePassModal.VerifySms
    val secondsLeft = when (val m = modal) {
        is ChangePassModal.VerifyMail -> m.secondsLeft
        is ChangePassModal.VerifySms -> m.secondsLeft
        else -> 0
    }

    CountdownEffect(
        isActive = isVerifyActive,
        secondsLeft = secondsLeft,
        onTick = { newS ->
            modal = when (val m = modal) {
                is ChangePassModal.VerifyMail -> m.copy(secondsLeft = newS)
                is ChangePassModal.VerifySms -> m.copy(secondsLeft = newS)
                else -> m
            }
        },
        onTimeout = {
            modal = when (val m = modal) {
                is ChangePassModal.VerifyMail ->
                    if (m.code.length < 6 && m.errorMessage.isNullOrBlank())
                        m.copy(errorMessage = "Tanımlanan sürede kodu girmediniz.")
                    else m

                is ChangePassModal.VerifySms ->
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
                modifier = Modifier.padding(start = 7.dp, end = 7.dp),
                title = "Şifre değiştir",
                onBackClick = onBack
            )
        }
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {
            Spacer(Modifier.height(UiDimens.xl))

            Spacer(Modifier.height(UiDimens.xl + UiDimens.s))
            Spacer(Modifier.height(UiDimens.xl + UiDimens.m))


            Text(
                text = "Şifren en az 8 karakter olmalı ve rakamlar, harfler ve özel karakterlerden (!\$@%?) oluşmalıdır.",
                color = colors.onSurfaceVariant,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )


            Spacer(Modifier.height(UiDimens.l))

            OutlinedTextField(
                value = currentPass,
                onValueChange = {
                    currentPass = it
                    if (!currentTouched) currentTouched = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = UiDimens.textFieldMinH),
                singleLine = true,
                label = { Text("Mevcut şifre") },
                isError = currentErr != null,
                visualTransformation = if (showCurrent) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { showCurrent = !showCurrent }) {
                        Icon(
                            imageVector = if (showCurrent) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = null,
                            tint = colors.onSurfaceVariant
                        )
                    }
                },
                supportingText = {
                    if (currentErr != null) {
                        Text(text = currentErr, color = colors.error, fontSize = 12.sp)
                    }
                },
                shape = RoundedCornerShape(UiDimens.rM),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.outline,
                    unfocusedBorderColor = colors.outlineVariant,
                    cursorColor = colors.primary,
                    focusedLabelColor = colors.primary
                )
            )

            Spacer(Modifier.height(UiDimens.m))

            OutlinedTextField(
                value = newPass,
                onValueChange = {
                    newPass = it
                    if (!newTouched) newTouched = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = UiDimens.textFieldMinH),
                singleLine = true,
                label = { Text("Yeni şifre") },
                isError = newErr != null,
                visualTransformation = if (showNew) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { showNew = !showNew }) {
                        Icon(
                            imageVector = if (showNew) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = null,
                            tint = colors.onSurfaceVariant
                        )
                    }
                },
                supportingText = {
                    if (newErr != null) {
                        Text(text = newErr, color = colors.error, fontSize = 12.sp)
                    }
                },
                shape = RoundedCornerShape(UiDimens.rM),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.outline,
                    unfocusedBorderColor = colors.outlineVariant,
                    cursorColor = colors.primary,
                    focusedLabelColor = colors.primary
                )
            )

            Spacer(Modifier.height(UiDimens.m))

            OutlinedTextField(
                value = confirmPass,
                onValueChange = {
                    confirmPass = it
                    if (!confirmTouched) confirmTouched = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = UiDimens.textFieldMinH),
                singleLine = true,
                label = { Text("Yeni şifreyi tekrar gir") },
                isError = confirmErr != null,
                visualTransformation = if (showConfirm) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { showConfirm = !showConfirm }) {
                        Icon(
                            imageVector = if (showConfirm) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = null,
                            tint = colors.onSurfaceVariant
                        )
                    }
                },
                supportingText = {
                    if (confirmErr != null) {
                        Text(text = confirmErr, color = colors.error, fontSize = 12.sp)
                    }
                },
                shape = RoundedCornerShape(UiDimens.rM),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.outline,
                    unfocusedBorderColor = colors.outlineVariant,
                    cursorColor = colors.primary,
                    focusedLabelColor = colors.primary
                )
            )

            Spacer(Modifier.height(UiDimens.xl))

            Button(
                onClick = {
                    submitAttempted = true

                    if (!isFormValid) return@Button

                    modal = ChangePassModal.ChooseMethod()
                },
                enabled = isFormValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(UiDimens.rM),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary,
                    contentColor = colors.onPrimary,
                    disabledContainerColor = colors.outlineVariant,
                    disabledContentColor = colors.onSurfaceVariant
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = "Kaydet",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(UiDimens.l))

            when (val m = modal) {
                ChangePassModal.None -> Unit

                is ChangePassModal.ChooseMethod -> {
                    Dialog(
                        onDismissRequest = {  },
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
                                    .padding(horizontal = 18.dp, vertical = 18.dp),
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
                                    text = "Şifreyi değiştirmek için SMS veya e-posta ile doğrulama yapmalısınız.",
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
                                        selected = selectedMethod == VerifyMethod.SMS,
                                        leadingIcon = { Icon(Icons.Outlined.Sms, contentDescription = null) },
                                        onClick = { selectedMethod = VerifyMethod.SMS }
                                    )
                                    MethodRow(
                                        title = "E-mail",
                                        selected = selectedMethod == VerifyMethod.EMAIL,
                                        leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                                        onClick = { selectedMethod = VerifyMethod.EMAIL }
                                    )
                                }

                                Spacer(Modifier.height(16.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    OutlinedButton(
                                        onClick = { modal = ChangePassModal.None },
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
                                                VerifyMethod.EMAIL -> {
                                                    if (email.isBlank()) {
                                                        ChangePassModal.ChooseMethod(errorMessage = "Kayıtlı e-posta bulunamadı.")
                                                    } else {
                                                        ChangePassModal.VerifyMail(
                                                            email = email,
                                                            secondsLeft = 59
                                                        )
                                                    }
                                                }

                                                VerifyMethod.SMS -> {
                                                    if (phone.isBlank()) {
                                                        ChangePassModal.ChooseMethod(errorMessage = "Kayıtlı telefon numarası bulunamadı.")
                                                    } else {
                                                        ChangePassModal.VerifySms(
                                                            phone = phone,
                                                            secondsLeft = 59
                                                        )
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

                is ChangePassModal.VerifyMail -> {
                    MailVerificationCode(
                        email = m.email,
                        code = m.code,
                        secondsLeft = m.secondsLeft,
                        errorMessage = m.errorMessage,
                        onCodeChange = { input ->
                            val filtered = input.filter(Char::isDigit).take(6)
                            modal = m.copy(code = filtered, errorMessage = null)
                        },
                        onDismiss = { modal = ChangePassModal.None },
                        onConfirm = {
                            if (m.code != "123456") {
                                modal = m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin.")
                            } else {
                                modal = ChangePassModal.None
                                onPasswordChanged()
                            }
                        },
                        onResend = { modal = m.copy(code = "", errorMessage = null, secondsLeft = 59) }
                    )
                }

                is ChangePassModal.VerifySms -> {
                    NumberVerificationCode(
                        phone = m.phone,
                        code = m.code,
                        secondsLeft = m.secondsLeft,
                        errorMessage = m.errorMessage,
                        onCodeChange = { input ->
                            val filtered = input.filter(Char::isDigit).take(6)
                            modal = m.copy(code = filtered, errorMessage = null)
                        },
                        onDismiss = { modal = ChangePassModal.None },
                        onConfirm = {
                            if (m.code != "123456") {
                                modal = m.copy(errorMessage = "Hatalı kod. Lütfen yeniden deneyin.")
                            } else {
                                modal = ChangePassModal.None
                                onPasswordChanged()
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

private fun validateNewPassword(pass: String): String? {
    val p = pass.trim()

    if (p.isBlank()) return "Yeni şifre boş olamaz."
    if (p.length < 8) return "Şifre en az 8 karakter olmalı."

    val hasLower = p.any { it.isLowerCase() }
    val hasUpper = p.any { it.isUpperCase() }
    val hasDigit = p.any { it.isDigit() }
    val allowedSpecial = setOf('!', '$', '@', '%', '?','.')
    val hasSpecial = p.any { it in allowedSpecial }

    if (!hasLower) return "En az 1 küçük harf olmalı."
    if (!hasUpper) return "En az 1 büyük harf olmalı."
    if (!hasDigit) return "En az 1 rakam olmalı."
    if (!hasSpecial) return "En az 1 özel karakter olmalı (!\$@%?)."

    val lower = p.lowercase()

    val blacklist = setOf(
        "12345678", "password", "qwerty", "asdfgh", "11111111", "00000000",
        "iloveyou", "admin123", "pass1234"
    )
    if (lower in blacklist) return "Bu şifre çok basit. Daha güçlü bir şifre seçin."

    if ("1234" in lower || "0000" in lower || "1111" in lower) {
        return "Bu şifre çok tahmin edilebilir."
    }

    if (p.toSet().size == 1) return "Bu şifre çok basit. Farklı karakterler kullanın."

    return null
}
