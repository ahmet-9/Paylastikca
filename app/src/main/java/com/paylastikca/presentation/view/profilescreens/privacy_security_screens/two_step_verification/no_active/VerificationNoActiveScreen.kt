package com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.ui.theme.authBlue

@Composable
fun VerificationNoActiveScreen(
    onBack: () -> Unit = {},
    onActivateClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme

    Scaffold(
        containerColor = colors.background,
        topBar = {
            BackButton(
                modifier = Modifier.padding(top = 25.dp, start = 7.dp),
                title = "İki adımlı doğrulama",
                onBackClick = onBack
            )
        }
    ) { padding ->

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val isShort = maxHeight < 700.dp
            val isTabletLike = maxWidth >= 600.dp

            val horizontalPadding = if (isTabletLike) 32.dp else 24.dp
            val topPadding = if (isShort) 12.dp else 25.dp

            val topSpacer = when {
                isShort -> 32.dp
                isTabletLike -> 90.dp
                else -> 70.dp
            }

            val buttonHeight = when {
                isShort -> 64.dp
                isTabletLike -> 78.dp
                else -> 74.dp
            }

            val bodyFont = if (isTabletLike) 15.sp else 14.sp
            val bodyLine = if (isTabletLike) 20.sp else 19.sp

            val contentWidth = if (isTabletLike) Modifier.fillMaxWidth(0.62f) else Modifier.fillMaxWidth()

            Column(
                modifier = Modifier
                    .then(contentWidth)
                    .align(Alignment.TopCenter)
                    .padding(horizontal = horizontalPadding)
                    .padding(top = topPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(topSpacer))

                Surface(
                    modifier = Modifier.size(70.dp),
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

                Spacer(Modifier.height(26.dp))

                Text(
                    text =
                        "Hesabınızı ekstra korumaya alın!\n" +
                                "İki adımlı doğrulama açıkken giriş yaparsanız\n" +
                                "şifrenize ek olarak size özel bir kod isteriz.\n" +
                                "Böylece hesabınız\n" +
                                "her zaman güvende olur.",
                    color = colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontSize = bodyFont,
                    lineHeight = bodyLine
                )

                Spacer(Modifier.height(28.dp))

                Button(
                    onClick = onActivateClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(buttonHeight)
                        .padding(top = 25.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = authBlue,
                        contentColor = colors.onPrimary
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Text(
                        text = "Aktifleştir",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(12.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                }

                Spacer(Modifier.weight(1f))
            }
        }
    }
}
