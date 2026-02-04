package com.paylastikca.presentation.view.loginscreens.onboardingscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.layout.FixedPage
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.ui.theme.BRİGHTOrange

@Composable
fun FirstScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val cfg = LocalConfiguration.current

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = BRİGHTOrange)
    }


    val isShort = cfg.screenHeightDp < 760


    val titleTop = if (isShort) 90.dp else 130.dp
    val buttonBottom = if (isShort) 24.dp else 40.dp

    PageScaffold(
        containerColor = BRİGHTOrange,
        applySafeInsets = true,
        removeTopInset = true
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BRİGHTOrange)
            ) {


                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Paylaştıkça’ya\nHoş Geldin!",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Black,
                        color = colors.onPrimary,
                        textAlign = TextAlign.Center,
                        lineHeight = 44.sp,
                        modifier = Modifier.padding(top = titleTop)
                    )

                    Text(
                        text = "Bir eşya, bir iyilik, bir mahalle… İyilik paylaştıkça büyür.\n",
                        fontSize = 22.sp,
                        color = colors.onPrimary,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(top = 20.dp, bottom = 30.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.onboardingfirstscreenimage),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .padding(top = 10.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }

                OnboardingIndicator(
                    pageCount = 5,
                    currentPage = 0,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = buttonBottom + 64.dp)
                )

                Button(
                    onClick = { navController.navigate("secondScreen") },
                    colors = ButtonDefaults.buttonColors(containerColor = colors.onPrimary),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = buttonBottom)
                        .widthIn(min = 280.dp, max = 420.dp)
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
    }
}
