package com.paylastikca.presentation.view.loginScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R
import com.paylastikca.presentation.view.commonUI.components.layout.rememberAdaptive
import com.paylastikca.ui.theme.authBlue

@Composable
fun ForgotYourPasswordScreen(
    onBackToLoginClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()

    var email by remember { mutableStateOf("") }

    Scaffold(containerColor = colors.background) { padding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(colors.background)
        ) {
            val isShort = maxHeight < 700.dp

            val topOffset = if (isShort) (-20).dp else (-50).dp
            val heroSize = if (isShort) 200.dp else 230.dp
            val heroOffsetY = if (isShort) 40.dp else 65.dp
            val topSpacer = if (isShort) 20.dp else 32.dp
            val afterTitle = if (isShort) 18.dp else 24.dp
            val illusH = if (isShort) 150.dp else 180.dp
            val bottomSpacer = if (isShort) 20.dp else 32.dp

            val contentWidthModifier =
                if (adaptive.maxContentWidth != null) {
                    Modifier
                        .fillMaxWidth()
                        .widthIn(max = adaptive.maxContentWidth)
                } else {
                    Modifier.fillMaxWidth()
                }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = contentWidthModifier
                        .offset(y = topOffset)
                        .padding(horizontal = 24.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(topSpacer))

                    Image(
                        painter = painterResource(id = R.drawable.signuploginpasswordscreenimage),
                        contentDescription = null,
                        modifier = Modifier
                            .size(heroSize)
                            .offset(y = heroOffsetY),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Şifreni Sıfırla",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 15.dp),
                        color = colors.onBackground
                    )

                    Spacer(modifier = Modifier.height(afterTitle))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("E-posta") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colors.outline,
                            unfocusedBorderColor = colors.outlineVariant,
                            cursorColor = colors.primary,
                            focusedLabelColor = colors.primary
                        )
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Image(
                        painter = painterResource(id = R.drawable.forgotyourpasswordscreenimage),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(illusH),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary,
                            contentColor = colors.onPrimary
                        )
                    ) {
                        Text(
                            text = "Doğrulama kodu gönder",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "E-posta adresini girdikten sonra, hesabını doğrulaman için sana bir kod göndereceğiz.",
                        fontSize = 12.sp,
                        color = colors.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Girişe dön",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = authBlue,
                        modifier = Modifier.clickable { onBackToLoginClick() }
                    )

                    Spacer(modifier = Modifier.height(bottomSpacer))
                }
            }
        }
    }
}
