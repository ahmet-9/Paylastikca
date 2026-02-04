package com.paylastikca.presentation.view.homepage_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.ScrollPage
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.platform.rememberPurchaseLauncher
import com.paylastikca.presentation.view.common_ui.components.platform.rememberSystemBarsController

private val ShopAccentPurpleBorder = Color(0xFF5D4FFF)

@Composable
fun ShoppingCentreScreen(navController: NavController) {
    val colors = MaterialTheme.colorScheme

    val systemBars = rememberSystemBarsController()
    SideEffect {
        systemBars.setSystemBarsColor(
            color = colors.inverseSurface,
            darkIcons = false
        )
    }

    val purchaseLauncher = rememberPurchaseLauncher()

    PageScaffold(
        containerColor = colors.inverseSurface
    ) { innerPadding ->

        val adaptive = rememberAdaptive()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colors.inverseSurface)
        ) {

            ShoppingCentreHeader(onBackClick = { navController.popBackStack() })


            ScrollPage(
                padding = PaddingValues(top = 0.dp),
                horizontalPadding = adaptive.pageHorizontalPadding,
                maxContentWidth = adaptive.maxContentWidth
            ) {

                Spacer(modifier = Modifier.height(56.dp))

                StoreHeader(balance = 10)

                CoinPacksGrid(
                    onBuy = { productId ->
                        purchaseLauncher.buy(
                            productId = productId,
                            onSuccess = { /* TODO: coin ekle */ },
                            onFailed = { /* TODO: hata */ }
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun ShoppingCentreHeader(onBackClick: () -> Unit) {
    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.inverseSurface)
            .padding(top = 8.dp)
    ) {
        BackButton(
            title = "",
            onBackClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,

                    contentDescription = "Geri",
            tint = colors.inverseOnSurface,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .clickable { onBackClick() }
        )
    }
}

@Composable
fun StoreHeader(balance: Int) {
    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.shoppingcentrescreen_p),
            contentDescription = null,
            modifier = Modifier
                .size(450.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-40).dp)
                .alpha(0.12f)
        )
        Image(
            painter = painterResource(id = R.drawable.shoppingcentrescreen_smallp),
            contentDescription = null,
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (13).dp, y = 40.dp)
                .alpha(0.10f)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.shoppingcentrescreen_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 80.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Mağaza",
                color = colors.inverseOnSurface,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .background(colors.inverseSurface, RoundedCornerShape(14.dp))
                    .border(
                        width = 2.dp,
                        color = ShopAccentPurpleBorder,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.shoppingcentrescreen_coinbadge),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = balance.toString(),
                    color = colors.inverseOnSurface,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CoinPackCard(
    amount: Int,
    priceText: String,
    coinRes: Int,
    pBadgeRes: Int,
    productId: String,
    onBuy: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Box(modifier = modifier.height(230.dp)) {
        Column(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(18.dp))
                .background(colors.inverseSurface)
                .border(
                    width = 2.dp,
                    color = ShopAccentPurpleBorder,
                    shape = RoundedCornerShape(18.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = coinRes),
                    contentDescription = null,
                    modifier = Modifier.size(130.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.onSurface)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = amount.toString(),
                        color = colors.inverseOnSurface,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Image(
                        painter = painterResource(id = pBadgeRes),
                        contentDescription = null,
                        modifier = Modifier.size(42.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(50))
                        .background(colors.primary)
                        .padding(horizontal = 18.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = priceText,
                        color = colors.onPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-10).dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colors.error)
                .clickable { onBuy(productId) }
                .padding(horizontal = 20.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Satın Al",
                color = colors.onError,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CoinPacksGrid(
    onBuy: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CoinPackCard(
                amount = 5,
                priceText = "₺ 19,99",
                coinRes = R.drawable.shoppingcentrescreen_collectivebadge,
                pBadgeRes = R.drawable.shoppingcentrescreen_coinbadge,
                productId = "coin_pack_5",
                onBuy = onBuy,
                modifier = Modifier.weight(1f)
            )
            CoinPackCard(
                amount = 15,
                priceText = "₺ 49,99",
                coinRes = R.drawable.shoppingcentrescreen_collectivebadge,
                pBadgeRes = R.drawable.shoppingcentrescreen_coinbadge,
                productId = "coin_pack_15",
                onBuy = onBuy,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CoinPackCard(
                amount = 25,
                priceText = "₺ 74,99",
                coinRes = R.drawable.shoppingcentrescreen_collectivebadge,
                pBadgeRes = R.drawable.shoppingcentrescreen_coinbadge,
                productId = "coin_pack_25",
                onBuy = onBuy,
                modifier = Modifier.weight(1f)
            )
            CoinPackCard(
                amount = 40,
                priceText = "₺ 94,99",
                coinRes = R.drawable.shoppingcentrescreen_collectivebadge,
                pBadgeRes = R.drawable.shoppingcentrescreen_coinbadge,
                productId = "coin_pack_40",
                onBuy = onBuy,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CoinPackCard(
                amount = 100,
                priceText = "₺ 199,99",
                coinRes = R.drawable.shoppingcentrescreen_collectivebadge,
                pBadgeRes = R.drawable.shoppingcentrescreen_coinbadge,
                productId = "coin_pack_100",
                onBuy = onBuy,
                modifier = Modifier.weight(1f)
            )
            CoinPackCard(
                amount = 200,
                priceText = "₺ 265,99",
                coinRes = R.drawable.shoppingcentrescreen_collectivebadge,
                pBadgeRes = R.drawable.shoppingcentrescreen_coinbadge,
                productId = "coin_pack_200",
                onBuy = onBuy,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
