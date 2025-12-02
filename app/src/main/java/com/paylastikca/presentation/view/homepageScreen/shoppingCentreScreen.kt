package com.paylastikca.presentation.view.homepageScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.paylastikca.app.R

import com.paylastikca.presentation.view.commonUI.components.PageHeader



@Composable
fun shoppingCentreScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()


    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color(0xFF282828),
            darkIcons = false
        )
    }

    Scaffold(
        containerColor = Color(0xFF282828)
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF282828))
        ) {
            ShoppingCentreHeader(onBackClick = { navController.popBackStack() })
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            ) {


                StoreHeader(balance = 10)



                CoinPacksGrid()

            }

        }
    }
}

@Composable
fun ShoppingCentreHeader(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF282828))
            .padding(top = 8.dp)
    ) {

        PageHeader(
            title = "",
            onBackClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        )


        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Geri",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .clickable { onBackClick() }
        )
    }
}

@Composable
fun StoreHeader(balance: Int) {

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
            modifier = Modifier
                .fillMaxWidth(),
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
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(20.dp))


            Row(
                modifier = Modifier
                    .background(Color(0xFF1D1A29), RoundedCornerShape(14.dp))
                    .border(
                        width = 2.dp,
                        color = Color(0xFF5D4FFF),
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
                    color = Color.White,
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
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(230.dp)
    ) {


        Column(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFF3A2F6E))
                .border(
                    width = 2.dp,
                    color = Color(0xFF6F5CFF),
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
                    .background(Color.Black)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = amount.toString(),
                        color = Color.White,
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
                        .background(Color(0xFFFF9800))
                        .padding(horizontal = 18.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = priceText,
                        color = Color.White,
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
                .background(Color(0xFFFF3D3D))
                .padding(horizontal = 20.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Satın Al",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@Composable
fun CoinPacksGrid() {
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
                modifier = Modifier.weight(1f)
            )
            CoinPackCard(
                amount = 15,
                priceText = "₺ 49,99",
                coinRes = R.drawable.shoppingcentrescreen_collectivebadge,
                pBadgeRes = R.drawable.shoppingcentrescreen_coinbadge,
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
                modifier = Modifier.weight(1f)
            )
            CoinPackCard(
                amount = 40,
                priceText = "₺ 94,99",
                coinRes = R.drawable.shoppingcentrescreen_collectivebadge,
                pBadgeRes = R.drawable.shoppingcentrescreen_coinbadge,
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
                modifier = Modifier.weight(1f)
            )
            CoinPackCard(
                amount = 200,
                priceText = "₺ 265,99",
                coinRes = R.drawable.shoppingcentrescreen_collectivebadge,
                pBadgeRes = R.drawable.shoppingcentrescreen_coinbadge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}






