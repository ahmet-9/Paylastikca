package com.paylastikca.presentation.view.homepageScreen

import androidx.compose.runtime.Composable


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.paylastikca.app.R

import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.animation.core.RepeatMode
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithCache

import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.zIndex
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng


import com.paylastikca.ui.theme.KalamFont
import kotlinx.coroutines.delay

import com.google.maps.android.compose.*


// Uygulamanın ana renkleri (görüntüye yakın tonlar)
private val PaylastikcaOrange = Color(0xFFE67E00)
private val PaylastikcaGreen  = Color(0xFF4CAF50)
private val PaylastikcaPurple = Color(0xFF7B1FA2)
private val CardGray          = Color(0xFFF3F3F3)



/*
@Composable
fun homePageMAİNScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    // 📌 Yükseklikleri ekran oranına göre ayarlıyoruz
    val mapHeight = (screenHeight * 0.50).dp     // Harita ekranın %35’i
    val buttonHeight = (screenHeight * 0.09).dp  // Butonlar ekranın %9’u

    Scaffold(
        containerColor = Color.White,
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                //.verticalScroll(rememberScrollState()) // ❌ kaldırabilirsin, scroll istemiyorsan
                .padding(horizontal = 16.dp)
        ) {
            // ADRES/ARAMA ÇUBUĞU
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = -10.dp, y = 0.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
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
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.LightGray,
                        disabledIndicatorColor = Color.LightGray,
                        cursorColor = Color.Black
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = 18.sp
                    )
                )

                Spacer(modifier = Modifier.width(1.dp))
                CoinBadge()
            }

            Spacer(Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
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

            // 📌 HARİTA (yüksekliği dinamik)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(mapHeight) // sabit dp yerine dinamik oran
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardGray),
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

            // 📌 Buton satırı (yüksekliği dinamik)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AdWatchButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(buttonHeight) // sabit 68.dp yerine dinamik
                )
                GiftWheelButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(buttonHeight)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TasksButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(buttonHeight)
                )
                StoreButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(buttonHeight)
                )
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}
*/


@Composable
fun homePageMAİNScreen() {


    Scaffold(
        containerColor = Color.White,
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            // ÜST BAŞLIK


            // ADRES/ARAMA ÇUBUĞU
            // ADRES + COIN ROW
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = -10.dp, y = 0.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp,),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Adres Çubuğu
                OutlinedTextField(
                    value = "konya/kadinhankarakayamah.../",
                    onValueChange = { },
                    modifier = Modifier

                        .weight(1f)   // coin badge için yer bırakır
                        .height(50.dp),
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.mapsicon),
                            contentDescription = "Konum",
                            modifier = Modifier.size(32.dp),
                            contentScale = ContentScale.Fit // resmi ölçekle
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,   // içi boş
                        unfocusedContainerColor = Color.Transparent, // içi boş
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Gray,          // focus stroke
                        unfocusedIndicatorColor = Color.LightGray,   // normal stroke
                        disabledIndicatorColor = Color.LightGray,
                        cursorColor = Color.Black
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = 18.sp
                    )
                )


                Spacer(modifier = Modifier.width(1.dp)) // Aradaki boşluk

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

            // HARİTA (placeholder) + turuncu pinler


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(459.dp)//485
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardGray),
                contentAlignment = Alignment.Center
            ) {
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(
                        LatLng(37.872, 32.492), // Konya örnek koordinat
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

            // 4 BUTON BLOĞU
            // 4 BUTON BLOĞU (tek satırda)
            // İlk satır: reklam izle & hediye çarkı
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp) // butonlar arası boşluk
            ) {


                /* BigActionButton(
                      bgColor = Color(0xFF3A683B),
                      topText = "hediye ",
                      bottomText = "çarkı",
                      modifier = Modifier.weight(1f)
                  )*/
                AdWatchButton(modifier = Modifier.weight(1f))
                GiftWheelButton(modifier = Modifier.weight(1f))

            }

            Spacer(modifier = Modifier.height(10.dp))

            // İkinci satır: görevler & mağaza
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
}


/** Sağ üstteki P rozeti (P 13P gibi) */
@Composable
fun CoinBadge() {
    Box(
        contentAlignment = Alignment.Center, // Yazıyı resmin ortasına yerleştir
        modifier = Modifier
            .size(80.dp)    // Resmin boyutunu ayarla (gerekirse değiştir)
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
            color = Color.Black,       // Rengine göre ayarlayabilirsin
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset(x = 12.dp, y = 1.dp)
        )
    }
}



/** “Adres Ekle / Konumu Doğrula” minik metin butonları */
@Composable
private fun SmallGhostAction(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color(0xFF6D6D6D)
    )
}

/** Turuncu/yeşil/siyah/mor büyük kutular */
@Composable
private fun BigActionButton(
    bgColor: Color,
    text: String,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    iconRes: Int? = null,
    textModifier: Modifier = Modifier // sadece bu butona özel yazı konumu
) {
    Surface(
        color = bgColor,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        modifier = modifier.height(80.dp) // 68dp yerine biraz daha yüksek yaptım
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            if (iconRes != null) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = text,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(bottom = 2.dp)
                )
            }

            Text(
                text = text, // 🔑 ilk parametre her zaman bu olacak
                modifier = textModifier.fillMaxWidth(), // 🔑 dışarıdan ayrı konumlandır
                color = textColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}


/** Alt gezinme: ana sayfa – yardımlaş – bildirim – profil (ikonlar yer tutucu) */
@Composable
 fun BottomBar() {
    // seçili olan index'i state olarak tutuyoruz
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
                onClick = { selectedIndex = index }, // tıklanınca state güncellenir



                icon = {
                    Icon(
                        painter = painterResource(id = item.first),
                        contentDescription = item.second,
                        modifier = Modifier.size(35.dp)
                    )
                },
                label = { Text(" ", fontSize = 10.sp) }, // etiketleri gizledim
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,           // seçili icon beyaz
                    unselectedIconColor = Color(0xFFE67E00),   // seçilmemiş turuncu
                    indicatorColor = Color(0xFFE67E00)         // arka plan turuncu
                )
            )
        }
    }
}

//finish

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
            modifier = Modifier.size(18.dp) // ikon boyutu
        )
        Spacer(modifier = Modifier.width(6.dp)) // ikon ile yazı arası
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )
    }
}

////////////////*5

@Composable
fun RotatingWheel(modifier: Modifier = Modifier) {
    // Sonsuz animasyon
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3500, easing = LinearEasing), // 4 saniyede tam tur
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Image(
        painter = painterResource(id = R.drawable.wheel), // çark görseli
        contentDescription = "Hediye Çarkı",
        modifier = modifier
            .size(70.dp) // çark boyutu
            .offset(x = 15.dp, y =15.dp )
            .graphicsLayer {
                rotationZ = rotation
                // 🔑 Dönme merkezi tam ortada
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
        // Buton kutusu
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(12.dp))

                .background(Color(0xFF3A683B)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hediye\nçarkı",
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(x = 13.dp, y = 0.dp)
            )
        }

        // Dönen çark → butonun üstünde
        RotatingWheel(
            modifier = Modifier
                .align(Alignment.TopStart) // konum
                .offset(x = (-15).dp, y = (-20).dp) // hassas ayar
                .zIndex(1f)
        )
    }
}


@Composable
fun AdWatchButton(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerX by infiniteTransition.animateFloat(
        initialValue = -80f,   // daha dar alanda başlasın
        targetValue = 80f,     // ikon sınırında bitsin
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
            .background(PaylastikcaOrange),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            // İkon + shimmer efekti kutu içine gömülü
            Box(
                modifier = Modifier
                    .size(45.dp) // ikon boyutu
                    .clip(RoundedCornerShape(8.dp)) // ✨ kenarları kırp
                    .clipToBounds(),
                contentAlignment = Alignment.Center
            ) {
                // Normal ikon
                Image(
                    painter = painterResource(id = R.drawable.videoicon),
                    contentDescription = "Reklam İzle",
                    modifier = Modifier.fillMaxSize()
                )

                // Shimmer overlay (ikon kutusu içine gömülü)
                Canvas(modifier = Modifier.fillMaxSize(0.5f)
                    .align(Alignment.Center)

                ) {
                    val gradient = Brush.linearGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Transparent,
                            0.45f to Color.Transparent,
                            0.5f to Color.White.copy(alpha = 0.6f), // tam orta (ince parlama)
                            0.55f to Color.Transparent,
                            1.0f to Color.Transparent
                        ),
                        start = Offset(shimmerX, 0f),
                        end = Offset(shimmerX + 220f, size.height) // genişlik alanı
                    )

                    drawRect(brush = gradient, size = size)
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Reklam izle\nkazan",
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}





/*------------
@Composable
fun AdWatchButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(175.dp)   // buton genişliği
            .height(68.dp)  // buton yüksekliği
            .clip(RoundedCornerShape(12.dp))
            .background(PaylastikcaOrange), // turuncu arka plan
        contentAlignment = Alignment.Center
    ) {
        // İçerikler (ikon + yazı)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            // İkon
            Image(
                painter = painterResource(id = R.drawable.videoicon),
                contentDescription = "Reklam İzle",
                modifier = Modifier.size(45.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Yazı (2 satır)
            Text(
                text = "Reklam izle\nkazan",
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}*/

@Composable
fun TasksButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(175.dp)   // buton genişliği
            .height(68.dp)  // buton yüksekliği
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Black), // görevler butonu siyah
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            // İkon (örneğin görev ikonun varsa)
            Image(
                painter = painterResource(id = R.drawable.taskicon), // görevler için ikon ekle
                contentDescription = "Görevler",
                modifier = Modifier
                    .size(32.dp) // boyut
                    .offset(x = (-2).dp, y = 0.dp) // konum ayarı
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Yazı
            Text(
                text = "Görevler",
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun StoreButton(modifier: Modifier = Modifier) {
    // 🔥 Rotasyon state
    val rotation = remember { Animatable(0f) }

    // 🔥 Sonsuz döngü: dön → bekle → dön
    LaunchedEffect(Unit) {
        while (true) {
            rotation.animateTo(
                targetValue = 360f,
                animationSpec = tween(1000, easing = LinearEasing) // 1 sn dönüş
            )
            rotation.snapTo(0f) // sıfırla
            delay(4000)         // 2 sn bekle
        }
    }

    Box(
        modifier = modifier
            .width(175.dp)   // buton genişliği
            .height(68.dp)   // buton yüksekliği
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Black), // mağaza butonu siyah
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // İkon (dikey dönen efektli)
            Image(
                painter = painterResource(id = R.drawable.storeicon), // mağaza için ikon
                contentDescription = "Mağaza",
                modifier = Modifier
                    .size(50.dp)
                    .offset(x = (-2).dp, y = 0.dp)
                    .graphicsLayer {
                        rotationY = rotation.value   // 🔑 Y ekseninde dön
                        cameraDistance = 12 * density // 🔑 3D derinlik
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Yazı
            Text(
                text = "Mağaza",

                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,

            )
        }
    }
}

















