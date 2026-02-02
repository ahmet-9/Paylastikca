package com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.TwoStepVerification.Active

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GppGood
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paylastikca.presentation.view.commonUI.components.layout.FixedPage
import com.paylastikca.presentation.view.commonUI.components.layout.PageScaffold
import com.paylastikca.presentation.view.commonUI.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.commonUI.components.navigation.PageHeader
import com.paylastikca.ui.UiDimens


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



@Composable
fun VerificationActive(
    navController: NavController,
    onRemoveClick: () -> Unit = {}
) {

    var showRemoveDialog by remember { mutableStateOf(false) }
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()





    PageScaffold(
        containerColor = colors.background
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = UiDimens.xl,
            maxContentWidth = adaptive.maxContentWidth
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = UiDimens.xl),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                PageHeader(
                    title = "iki adımlı doğrulama",
                    modifier = Modifier.fillMaxWidth(),
                    onBackClick = { navController.popBackStack() }
                )


                Spacer(modifier = Modifier.weight(0.55f))


                Icon(
                    imageVector = Icons.Filled.GppGood,
                    contentDescription = null,
                    tint = colors.tertiary,
                    modifier = Modifier.size(210.dp)
                )


                Spacer(modifier = Modifier.height(38.dp))


                Text(
                    text = "İki adımlı doğrulama\naktif !",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onBackground,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp
                )


                Spacer(modifier = Modifier.height(36.dp))


                Spacer(modifier = Modifier.height(60.dp))

                Text(
                    text = "Kaldır",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.error,
                    modifier = Modifier
                        .clickable {  navController.navigate("verificationActiveRemove") }
                        .padding(vertical = UiDimens.s, horizontal = UiDimens.m)
                )

                Spacer(modifier = Modifier.weight(1.2f))
            }
        }
    }
}
