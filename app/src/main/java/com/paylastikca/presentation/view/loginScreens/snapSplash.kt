package com.paylastikca.presentation.view.loginScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paylastikca.app.R
import com.paylastikca.ui.theme.BRİGHTOrange

@Composable
fun snapSplash() {

    val colors = MaterialTheme.colorScheme
    val cfg = LocalConfiguration.current

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = BRİGHTOrange)
    }


    val isShort = cfg.screenHeightDp < 760
    val logoTop = if (isShort) 140.dp else 230.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BRİGHTOrange)
            .padding(bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(logoTop))

        Image(
            painter = painterResource(id = R.drawable.snapsplashlogo),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.75f)
        )

        Spacer(modifier = Modifier.weight(1f))

        CircularProgressIndicator(
            color = colors.onPrimary,
            strokeWidth = 3.dp
        )
    }
}
