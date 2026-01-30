package com.paylastikca.presentation.view.homepageScreen.giftWheel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R
import com.paylastikca.presentation.view.commonUI.components.layout.FixedPage
import com.paylastikca.presentation.view.commonUI.components.layout.PageScaffold
import com.paylastikca.presentation.view.commonUI.components.layout.rememberAdaptive
import com.paylastikca.ui.UiDimens

@Composable
fun congratulationsScreen(
    prizeText: String = "1 çip",
    onDismiss: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()

    PageScaffold(
        containerColor = colors.background
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth,

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = UiDimens.xl),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.congratulationsscreenicon),
                    contentDescription = "Trophy",
                    modifier = Modifier.size(180.dp)
                )

                Spacer(modifier = Modifier.height(UiDimens.l))

                Text(
                    text = "TEBRİKLER",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onBackground
                )

                Spacer(modifier = Modifier.height(UiDimens.s))

                Text(
                    text = "$prizeText Kazandınız",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    color = colors.onBackground
                )

                Spacer(modifier = Modifier.height(UiDimens.xl))

                Image(
                    painter = painterResource(id = R.drawable.shoppingcentrescreen_coinbadge),
                    contentDescription = "Chip",
                    modifier = Modifier.size(165.dp)
                )

                Spacer(modifier = Modifier.height(UiDimens.xl))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(UiDimens.actionButtonH),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = colors.onPrimary
                    )
                ) {
                    Text(
                        text = "TAMAM",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 3.sp,
                        color = colors.onPrimary
                    )
                }
            }
        }
    }
}
