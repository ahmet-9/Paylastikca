package com.paylastikca.presentation.view.loginscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R
import com.paylastikca.ui.theme.authBlue
import com.paylastikca.ui.theme.authBlueDisabled

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordRepeatVisible by remember { mutableStateOf(false) }
    var termsAccepted by remember { mutableStateOf(false) }

    val isLengthValid = password.length >= 8
    val containsLetter = password.any { it.isLetter() }
    val containsDigit = password.any { it.isDigit() }
    val isPasswordValid = isLengthValid && containsLetter && containsDigit
    val isRepeatOk = passwordRepeat.isNotEmpty() && passwordRepeat == password

    Scaffold(
        containerColor = colors.background
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(colors.background)
        ) {


            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.signuploginpasswordscreenimage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(110.dp),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = "Paylaştıkça",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Üye ol",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Ad Soyad") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.usericon),
                            contentDescription = null,
                            tint = colors.onSurfaceVariant,
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colors.outline,
                        unfocusedBorderColor = colors.outlineVariant,
                        cursorColor = colors.primary,
                        focusedLabelColor = colors.primary
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-posta") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.mailicon),
                            contentDescription = null,
                            tint = colors.onSurfaceVariant,
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colors.outline,
                        unfocusedBorderColor = colors.outlineVariant,
                        cursorColor = colors.primary,
                        focusedLabelColor = colors.primary
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Şifre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.lockicon),
                            contentDescription = null,
                            tint = colors.onSurfaceVariant,
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colors.outline,
                        unfocusedBorderColor = colors.outlineVariant,
                        cursorColor = colors.primary,
                        focusedLabelColor = colors.primary
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = null,
                                tint = colors.onSurfaceVariant
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PasswordRequirementItem("En az 8 karakter", isLengthValid)
                    PasswordRequirementItem("En az 1 harf", containsLetter)
                    PasswordRequirementItem("En az 1 rakam", containsDigit)
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = passwordRepeat,
                    onValueChange = { passwordRepeat = it },
                    label = { Text("Şifre (tekrar)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.lockicon),
                            contentDescription = null,
                            tint = colors.onSurfaceVariant,
                            modifier = Modifier.size(27.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colors.outline,
                        unfocusedBorderColor = colors.outlineVariant,
                        cursorColor = colors.primary,
                        focusedLabelColor = colors.primary
                    ),
                    visualTransformation = if (passwordRepeatVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (isRepeatOk) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = colors.tertiary
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                            }
                            IconButton(onClick = { passwordRepeatVisible = !passwordRepeatVisible }) {
                                Icon(
                                    imageVector = if (passwordRepeatVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                    contentDescription = null,
                                    tint = colors.onSurfaceVariant
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = { termsAccepted = it }
                    )
                    Column {
                        Row {
                            Text(
                                text = "Kullanım şartlarını",
                                color = authBlue,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable { }
                            )

                            Text(text = " ve ", fontSize = 13.sp, color = colors.onSurface)

                            Text(
                                text = "Gizlilik politikasını",
                                color = authBlue,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable { }
                            )
                        }

                        Text(
                            text = "kabul ediyorum",
                            fontSize = 13.sp,
                            color = colors.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = { onSignUpClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = termsAccepted && isPasswordValid && passwordRepeat == password,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = authBlue,
                        disabledContainerColor = authBlueDisabled
                    )
                ) {
                    Text(text = "Üye ol", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f), color = colors.outlineVariant)
                    Text(text = "  veya  ", fontSize = 13.sp, color = colors.onSurfaceVariant)
                    HorizontalDivider(modifier = Modifier.weight(1f), color = colors.outlineVariant)
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { onGoogleClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = ButtonDefaults.outlinedButtonBorder
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(colors.surface)
                            .shadow(1.dp, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_g),
                            contentDescription = "Google logo",
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Google ile devam et", fontSize = 15.sp, color = colors.onSurface)
                }

                Spacer(modifier = Modifier.height(18.dp))
            }


            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 18.dp, top = 10.dp)
            ) {
                Text(
                    text = "Zaten hesabın var mı? ",
                    fontSize = 13.sp,
                    color = colors.onSurfaceVariant
                )
                Text(
                    text = "Giriş yap",
                    fontSize = 16.sp,
                    color = authBlue,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.None,
                    modifier = Modifier.clickable { onSignInClick() }
                )
            }
        }
    }
}

@Composable
fun PasswordRequirementItem(text: String, isValid: Boolean) {
    val colors = MaterialTheme.colorScheme

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = if (isValid) Icons.Default.Check else Icons.Default.Close,
            contentDescription = null,
            tint = if (isValid) colors.tertiary else colors.error
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            color = if (isValid) colors.tertiary else colors.error
        )
    }
}
