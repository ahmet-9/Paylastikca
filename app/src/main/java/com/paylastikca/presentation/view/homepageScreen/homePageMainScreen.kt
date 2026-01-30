package com.paylastikca.presentation.view.homepageScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paylastikca.app.R
import com.paylastikca.presentation.view.commonUI.components.layout.FixedPage
import com.paylastikca.presentation.view.commonUI.components.HomeMap
import com.paylastikca.presentation.view.commonUI.components.layout.PageScaffold
import com.paylastikca.presentation.view.commonUI.components.platform.rememberRewardedAdLauncher
import com.paylastikca.ui.UiDimens
import com.paylastikca.ui.theme.Gray
import kotlinx.coroutines.delay

@Composable
fun homePageMainScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme

    var showDialog by remember { mutableStateOf(false) }
    val rewardedAdLauncher = rememberRewardedAdLauncher()

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colors.background
        )
    }

    PageScaffold(
        containerColor = MaterialTheme.colorScheme.background,
        currentRoute = "homePageMainScreen",
        navController = navController,
        bottomBarContainerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->

        FixedPage(
            padding = innerPadding,
            horizontalPadding = UiDimens.l
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = UiDimens.xs),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                OutlinedTextField(
                    value = "konya/kadinhankarakayamah.../",
                    onValueChange = { },
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(minHeight = UiDimens.textFieldMinH),
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.mapsicon),
                            contentDescription = "Konum",
                            modifier = Modifier.size(UiDimens.icon32),
                            contentScale = ContentScale.Fit
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(UiDimens.rS),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Gray,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                        disabledIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = 18.sp
                    )
                )

                Spacer(modifier = Modifier.width(UiDimens.s))

                CoinBadge()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = UiDimens.xs, bottom = UiDimens.m),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconTextButton(
                    iconRes = R.drawable.plusicon,
                    text = "Adres Ekle"
                )
                Spacer(Modifier.width(UiDimens.xl))
                IconTextButton(
                    iconRes = R.drawable.restarticon,
                    text = "Konumu Doğrula"
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .heightIn(min = UiDimens.mapMinH)
                    .clip(RoundedCornerShape(UiDimens.rL))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                HomeMap(
                    modifier = Modifier.matchParentSize()
                )
            }


            Spacer(Modifier.height(UiDimens.l))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(UiDimens.buttonGap)
            ) {
                AdWatchButton(
                    modifier = Modifier.weight(1f),
                    onClick = { showDialog = true }
                )
                GiftWheelButton(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(UiDimens.s))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(UiDimens.buttonGap)
            ) {
                TasksButton(modifier = Modifier.weight(1f))
                StoreButton(modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.height(UiDimens.xs))
        }
    }

    WatchAdsScreen(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onWatchAd = {
            rewardedAdLauncher.show(
                onReward = {
                    // TODO: ödül ver (chip artır vs)
                },
                onClosed = {

                },
                onFailed = {
                    // TODO: hata mesajı/toast (şimdilik boş)
                }
            )
        }

    )
}

@Composable
fun CoinBadge() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(UiDimens.coinSize)
            .offset(x = 4.dp, y = 0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.coin_badge),
            contentDescription = "Coin Badge",
            contentScale = ContentScale.Fit,

            modifier = Modifier.size(UiDimens.coinSize)
        )
        Text(
            text = "13",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset(x = 12.dp, y = 1.dp)
        )
    }
}

@Composable
fun IconTextButton(
    iconRes: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(UiDimens.icon18)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun RotatingWheel(modifier: Modifier = Modifier) {

    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Image(
        painter = painterResource(id = R.drawable.wheel),
        contentDescription = "Hediye Çarkı",
        modifier = modifier
            .size(70.dp)
            .offset(x = 15.dp, y = 15.dp)
            .graphicsLayer {
                rotationZ = rotation
                transformOrigin = TransformOrigin(0.47f, 0.55f)
            }
    )
}

@Composable
fun GiftWheelButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.height(UiDimens.buttonH),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(UiDimens.rM))
                .background(MaterialTheme.colorScheme.tertiary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hediye\nçarkı",
                color = MaterialTheme.colorScheme.onTertiary,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(x = 13.dp, y = 0.dp)
            )
        }

        RotatingWheel(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-15).dp, y = (-20).dp)
                .zIndex(1f)
        )
    }
}

@Composable
fun AdWatchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerX by infiniteTransition.animateFloat(
        initialValue = -80f,
        targetValue = 80f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerAnim"
    )

    Box(
        modifier = modifier
            .height(UiDimens.buttonH)
            .clip(RoundedCornerShape(UiDimens.rM))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(UiDimens.icon45)
                    .clip(RoundedCornerShape(UiDimens.rS))
                    .clipToBounds(),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.videoicon),
                    contentDescription = "Reklam İzle",
                    modifier = Modifier.fillMaxSize()
                )

                Canvas(
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                        .align(Alignment.Center)
                ) {
                    val gradient = Brush.linearGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Transparent,
                            0.45f to Color.Transparent,
                            0.5f to Color.White.copy(alpha = 0.6f),
                            0.55f to Color.Transparent,
                            1.0f to Color.Transparent
                        ),
                        start = Offset(shimmerX, 0f),
                        end = Offset(shimmerX + 220f, size.height)
                    )
                    drawRect(brush = gradient, size = size)
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Reklam izle\nkazan",
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TasksButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(UiDimens.buttonH)
            .clip(RoundedCornerShape(UiDimens.rM))
            .background(MaterialTheme.colorScheme.inverseSurface),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.taskicon),
                contentDescription = "Görevler",
                modifier = Modifier
                    .size(UiDimens.icon32)
                    .offset(x = (-2).dp, y = 0.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Görevler",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun StoreButton(modifier: Modifier = Modifier) {

    val rotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            rotation.animateTo(
                targetValue = 360f,
                animationSpec = tween(1000, easing = LinearEasing)
            )
            rotation.snapTo(0f)
            delay(4000)
        }
    }

    Box(
        modifier = modifier
            .height(UiDimens.buttonH)
            .clip(RoundedCornerShape(UiDimens.rM))
            .background(MaterialTheme.colorScheme.inverseSurface),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.storeicon),
                contentDescription = "Mağaza",
                modifier = Modifier
                    .size(UiDimens.icon50)
                    .offset(x = (-2).dp, y = 0.dp)
                    .graphicsLayer {
                        rotationY = rotation.value
                        cameraDistance = 12 * density
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Mağaza",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
