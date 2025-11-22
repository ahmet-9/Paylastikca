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

@Composable
fun fifthScreen(navController: NavController){

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color(0xFFFF8E0D)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8E0D)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {




        Image(
            painter = painterResource(id = R.drawable.onboardingfifthscreenimage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(top = 180.dp)
                .clip(RoundedCornerShape(40.dp))
        )


        Text(
            text = "Paylaş, kazan",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 44.sp,
            modifier = Modifier.padding(top = 20.dp)
        )


        Text(
            text = "Yardımlaştıkça çip kazan,\n" +
                    "görevleri tamamla ve\n"+
                    "ödülleri topla.",
            fontSize = 22.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier.padding(top = 20.dp, bottom = 30.dp)
        )



        OnboardingIndicator(
            pageCount = 5,
            currentPage = 4,
            modifier = Modifier
                .padding(top = 193.dp, bottom = 6.dp)
        )

        Spacer(modifier = Modifier.weight(1f))




        Button(
            onClick = { navController.navigate("firstScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .padding(bottom = 40.dp)
                .width(280.dp)
                .height(50.dp)
        ) {
            Text(
                text = "BAŞLA",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}