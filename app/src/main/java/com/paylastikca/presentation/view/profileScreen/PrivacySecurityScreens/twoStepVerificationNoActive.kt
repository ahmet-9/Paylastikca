package com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens

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
import com.paylastikca.presentation.view.commonUI.components.PageHeader
import com.paylastikca.ui.theme.authBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun twoStepVerificationNoActive(
    onBack: () -> Unit = {},
    onActivateClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            PageHeader(
                modifier = Modifier.padding(top = 25.dp, start = 7.dp),
                title = "İki adımlı doğrulama",
                onBackClick = onBack
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp,)
                .padding(top = 25.dp ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(70.dp))

            // Icon circle (theme primary)
            Surface(
                modifier = Modifier.size(70.dp),
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

            Spacer(Modifier.height(26.dp))

            Text(
                text = "Hesabınızı ekstra korumaya alın!\n" +
                        "İki adımlı doğrulama açıkken giriş yaparsanız\n" +
                        "şifrenize ek olarak size özel bir kod isteriz.\n" +
                        "Böylece hesabınız\n" +
                        "her zaman güvende olur.",
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                lineHeight = 19.sp
            )

            Spacer(Modifier.height(28.dp))

            // Button (theme primary)
            Button(
                onClick = onActivateClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(74.dp)
                    .padding(top = 25.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = authBlue,
                    contentColor = MaterialTheme.colorScheme.onPrimary
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
