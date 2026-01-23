package com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.TwoStepVerification

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GppGood
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.ui.theme.authBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationCompleted(

    onDone: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp,)
                .offset(y = (-140).dp)
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = Icons.Filled.GppGood,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(210.dp)
            )

            Spacer(Modifier.height(38.dp))

            Text(
                text = "İki adımlı doğrulama\netkinleştirildi !",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                lineHeight = 35.sp,


            )

            Spacer(Modifier.height(36.dp))






            val buttonColor = authBlue

            Button(
                onClick = onDone,
                modifier = Modifier
                    .fillMaxWidth(0.70f)
                    .height(60.dp)
                    .offset(y = 60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Bitti",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                    )
            }

            Spacer(Modifier.weight(1.2f))
        }
    }
}
