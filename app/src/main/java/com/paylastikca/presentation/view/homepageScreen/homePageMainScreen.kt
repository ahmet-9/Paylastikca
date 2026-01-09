package com.paylastikca.presentation.view.homepageScreen

import androidx.compose.runtime.Composable


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.paylastikca.app.R
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.animation.core.RepeatMode
import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.zIndex
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import com.google.maps.android.compose.*
import com.paylastikca.ui.theme.Gray







@Composable
fun homePageMainScreen() {

    var showDialog by remember { mutableStateOf(false) } // Rİ

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
       // bottomBar = { BottomBar() }
        bottomBar = {
            com.paylastikca.presentation.view.commonUI.components.BottomBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {



            // address search bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = -10.dp, y = 0.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp,),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                OutlinedTextField(
                    value = "konya/kadinhankarakayamah.../",
                    onValueChange = { },
                    modifier = Modifier

                        .weight(1f)
                        .height(50.dp),
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.mapsicon),
                            contentDescription = "Konum",
                            modifier = Modifier.size(32.dp),
                            contentScale = ContentScale.Fit
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
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


                Spacer(modifier = Modifier.width(1.dp))

                // Coin Badge
                CoinBadge()
            }


            Spacer(Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
                    .offset(x = 3.dp, y = -20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconTextButton(
                    iconRes = R.drawable.plusicon,
                    text = "Adres Ekle"
                )
                Spacer(Modifier.width(24.dp))
                IconTextButton(
                    iconRes = R.drawable.restarticon,
                    text = "Konumu Doğrula"
                )
            }



            Spacer(Modifier.height(12.dp))




            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(459.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(
                        LatLng(37.872, 32.492),
                        14f
                    )
                }

                GoogleMap(
                    modifier = Modifier.matchParentSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(position = LatLng(37.872, 32.492)),
                        title = "Benim Konumum",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    )
                }
            }



            Spacer(Modifier.height(16.dp))

            // four button blocks
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {



                AdWatchButton(    //Ri
                    modifier = Modifier.weight(1f),
                    onClick = { showDialog = true }
                )

               // AdWatchButton(modifier = Modifier.weight(1f))  //old
                GiftWheelButton(modifier = Modifier.weight(1f))

            }

            Spacer(modifier = Modifier.height(10.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TasksButton(modifier = Modifier.weight(1f))
                StoreButton(modifier = Modifier.weight(1f))


            }



            Spacer(Modifier.height(12.dp))
        }
    }


    //Rİ
    WatchAdsScreen(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onWatchAd = {

        }
    )

}


//coin badge
@Composable
fun CoinBadge() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(80.dp)
            .offset(x = 7.dp, y = 0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.coin_badge),
            contentDescription = "Coin Badge",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(150.dp)
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




/*
//bottombar menu
@Composable
 fun BottomBar() {

    var selectedIndex by remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp,
        modifier = Modifier.height(70.dp),
        windowInsets = WindowInsets(0)
    ) {
        val items = listOf(
            Pair(R.drawable.homeiconmenu, "Ana Sayfa"),
            Pair(R.drawable.cooperationiconmenu, "Yardımlaş"),
            Pair(R.drawable.notificationiconmenu, "Bildirim"),
            Pair(R.drawable.profileiconmenu, "Profil")
        )

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },



                icon = {
                    Icon(
                        painter = painterResource(id = item.first),
                        contentDescription = item.second,
                        modifier = Modifier.size(35.dp)
                    )
                },
                label = { Text(" ", fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color(0xFFE67E00),
                    indicatorColor = Color(0xFFE67E00)
                )
            )
        }
    }
}
*/


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
            modifier = Modifier.size(18.dp)
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
            .offset(x = 15.dp, y =15.dp )
            .graphicsLayer {
                rotationZ = rotation

                transformOrigin = TransformOrigin(0.47f, 0.55f)
            }
    )
}

@Composable
fun GiftWheelButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(175.dp)
            .height(68.dp),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(12.dp))

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

        // spinning wheel
        RotatingWheel(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-15).dp, y = (-20).dp)
                .zIndex(1f)
        )
    }
}


@Composable
fun AdWatchButton(modifier: Modifier = Modifier,
                  onClick: () -> Unit  //Rİ

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
            .width(175.dp)
            .height(68.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick() }, //Rİ
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clipToBounds(),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.videoicon),
                    contentDescription = "Reklam İzle",
                    modifier = Modifier.fillMaxSize()
                )


                Canvas(modifier = Modifier.fillMaxSize(0.5f)
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
            .width(175.dp)
            .height(68.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.inverseSurface),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.taskicon),
                contentDescription = "Görevler",
                modifier = Modifier
                    .size(32.dp)
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
            .width(175.dp)
            .height(68.dp)
            .clip(RoundedCornerShape(12.dp))
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
                    .size(50.dp)
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
                textAlign = TextAlign.Center,

            )
        }
    }


}


















