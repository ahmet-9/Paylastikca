package com.paylastikca.presentation.view.loginScreens.onboardingScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paylastikca.app.R
import com.paylastikca.ui.theme.BRİGHTOrange

@Composable
fun thirdScreen(navController: NavController){

    val colors = MaterialTheme.colorScheme

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = BRİGHTOrange
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BRİGHTOrange),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Fazla eşyanı paylaş",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onPrimary,
            textAlign = TextAlign.Center,
            lineHeight = 44.sp,
            modifier = Modifier.padding(top = 130.dp)
        )


        Text(
            text = "İhtiyacın olmayan eşyaları paylaş,\n" +
                    "topluluğa katkı sağla.",
            fontSize = 22.sp,
            color = colors.onPrimary,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier.padding(top = 20.dp, bottom = 40.dp)
        )


        Image(
            painter = painterResource(id = R.drawable.onboardingthirdscreenimage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(top = 10.dp)
            .clip(RoundedCornerShape(40.dp))
        )

        OnboardingIndicator(
            pageCount = 5,
            currentPage = 2,
            modifier = Modifier
                .padding(top = 139.dp, bottom = 6.dp)
        )

        Spacer(modifier = Modifier.weight(1f))




        Button(
            onClick = { navController.navigate("fourthScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.onPrimary
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .padding(bottom = 40.dp)
                .width(280.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Geç",
                color = colors.onPrimaryContainer,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}