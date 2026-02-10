package com.paylastikca.presentation.view.sharingscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.SwapHoriz


import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import com.paylastikca.ui.UiDimens

@Composable
fun SharingMainScreen(
    navController: NavController,
    currentRoute: String = "SharingMainScreen",
    onHelpClick: () -> Unit = {},
    onHelpListClick: () -> Unit = {},
    onRequestClick: () -> Unit = {},
    onRequestListClick: () -> Unit = {},
    onSwapClick: () -> Unit = {},
    onSwapListClick: () -> Unit = {}
) {



    val colors = MaterialTheme.colorScheme
    val adaptive = rememberAdaptive()
    val s = adaptive.scale

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(colors.primaryContainer)
    }

    PageScaffold(
        containerColor = colors.primaryContainer,
        currentRoute = currentRoute,
        navController = navController,
        bottomBarContainerColor = colors.primaryContainer,
        removeTopInset = true
    ) { innerPadding ->

        val contentTopShift = (48f * s).dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = contentTopShift)
        ) {

            HeaderImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((260f * s).dp)
            )

            FixedPage(
                padding = PaddingValues(top = 0.dp),
                horizontalPadding = adaptive.pageHorizontalPadding,
                maxContentWidth = adaptive.maxContentWidth
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = (UiDimens.s.value * s).dp,
                            bottom = (UiDimens.s.value * s).dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(Modifier.height((14f * s).dp))

                    CenterNotifyIcon(
                        badgeCount = 1,
                        modifier = Modifier.padding(bottom = (12f * s).dp)
                    )

                    Spacer(Modifier.height((8f * s).dp))

                    ActionRow(
                        scale = s,
                        leftIcon = Icons.Filled.Favorite,
                        leftText = "Yardım Et",
                        rightText = "Yardım Listem",
                        onLeftClick = onHelpClick,
                        onRightClick = onHelpListClick
                    )

                    Spacer(Modifier.height((14f * s).dp))

                    ActionRow(
                        scale = s,
                        leftIcon = Icons.Filled.CheckCircle,
                        leftText = "Talep Et",
                        rightText = "Talep Listem",
                        onLeftClick = onRequestClick,
                        onRightClick = onRequestListClick
                    )

                    Spacer(Modifier.height((14f * s).dp))

                    ActionRow(
                        scale = s,
                        leftIcon = Icons.Filled.SwapHoriz,
                        leftText = "Takas",
                        rightText = "Takas Listem",
                        onLeftClick = onSwapClick,
                        onRightClick = onSwapListClick
                    )

                    Spacer(Modifier.height((24f * s).dp))
                }
            }
        }
    }

}

@Composable
private fun HeaderImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.handshake_header),
        contentDescription = "Paylaştıkça",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
private fun CenterNotifyIcon(
    badgeCount: Int,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(colors.primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Send,
                contentDescription = "Bildirim",
                tint = colors.onPrimary,
                modifier = Modifier.size(26.dp)
            )
        }

        if (badgeCount > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(colors.error),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badgeCount.toString(),
                    color = colors.onError,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun ActionRow(
    scale: Float,
    leftIcon: androidx.compose.ui.graphics.vector.ImageVector,
    leftText: String,
    rightText: String,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    val rowH = (66f * scale).dp
    val radius = (18f * scale).dp

    val sideIconSize = (34f * scale).dp
    val iconGap = (10f * scale).dp
    val betweenButtonsGap = (12f * scale).dp

    val leftTextSize = (23f * scale).sp


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = rowH),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = leftIcon,
            contentDescription = leftText,
            tint = colors.primary,
            modifier = Modifier.size(sideIconSize)
        )

        Spacer(Modifier.width(iconGap))

        Button(
            onClick = onLeftClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary
            ),
            shape = RoundedCornerShape(radius),
            contentPadding = PaddingValues(horizontal = (18f * scale).dp, vertical = 0.dp),
            modifier = Modifier
                .weight(1f)
                .height(rowH)
        ) {
            Text(
                text = leftText,
                fontSize = leftTextSize,
                fontWeight = FontWeight.Black
            )
        }

        Spacer(Modifier.width(betweenButtonsGap))

        OutlinedButton(
            onClick = onRightClick,
            shape = RoundedCornerShape(radius),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, colors.primary),
            contentPadding = PaddingValues(
                horizontal = (14f * scale).dp,
                vertical = (8f * scale).dp
            ),
            modifier = Modifier
                .weight(1f)
                .defaultMinSize(minHeight = rowH)
        ) {
            Text(
                text = rightText,
                fontSize = (20f * scale).sp,
                lineHeight = (22f * scale).sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.primary,
                textAlign = TextAlign.Center,
                softWrap = true,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth()
            )
        }


    }
}
