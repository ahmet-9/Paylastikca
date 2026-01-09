package com.paylastikca.presentation.view.homepageScreen.giftWheel

import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R

@Composable
fun congratulationsScreen(
    prizeText: String = "1 çip",   
    onDismiss: () -> Unit = {}
) {
    val scaleFactor = 1.8f
    val colors = MaterialTheme.colorScheme

    Scaffold(

        containerColor = colors.background,

        bottomBar = {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = (12 * scaleFactor).dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {}
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding((24 * scaleFactor).dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Kupa resmi
            Image(
                painter = painterResource(id = R.drawable.congratulationsscreenicon),
                contentDescription = "Trophy",
                modifier = Modifier.size((120 * scaleFactor).dp)
            )

            Spacer(modifier = Modifier.height((16 * scaleFactor).dp))

            Text(
                text = "TEBRİKLER",
                fontSize = (22 * scaleFactor).sp,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground
            )

            Spacer(modifier = Modifier.height((8 * scaleFactor).dp))

            Text(
                text = "$prizeText Kazandınız",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2, // 2 satıra izin ver
                color = colors.onBackground
            )



            Spacer(modifier = Modifier.height((24 * scaleFactor).dp))

            // Mor çip simgesi (yuvarlak + P harfi)
            Image(
                painter = painterResource(id = R.drawable.shoppingcentrescreen_coinbadge), // 👈 kendi drawable'ını koy
                contentDescription = "Chip",
                modifier = Modifier
                    .size((92 * scaleFactor).dp) // ✅ aynı boyut
            )

            Spacer(modifier = Modifier.height((24 * scaleFactor).dp))

            // TAMAM butonu
            Button(
                onClick = { onDismiss() },
                modifier = Modifier.fillMaxWidth(0.8f)
                    .height(63.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = colors.primary, contentColor = colors.onPrimary)
            ) {
                Text(
                    "TAMAM",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = (16 * scaleFactor).sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 3.sp
                )
            }
        }
    }
}
