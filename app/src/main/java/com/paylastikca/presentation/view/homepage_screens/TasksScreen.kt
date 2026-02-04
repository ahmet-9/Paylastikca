package com.paylastikca.presentation.view.homepage_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*

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
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton




import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.ScrollPage
import com.paylastikca.presentation.view.common_ui.components.platform.rememberClipboardHelper
import com.paylastikca.presentation.view.common_ui.components.platform.rememberShareHelper
import com.paylastikca.ui.UiDimens

import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive




@Composable
fun TasksScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme

    PageScaffold(
        containerColor = colors.background

    ) { innerPadding ->

        val adaptive = rememberAdaptive()

        ScrollPage(
            padding = innerPadding,
            horizontalPadding = adaptive.pageHorizontalPadding,
            maxContentWidth = adaptive.maxContentWidth
        ) {

            BackButton(
                title = "Görevler",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = UiDimens.xl),
                onBackClick = { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(UiDimens.m))

            InviteFriendCard(inviteLink = "https://www.paylastik")

            Spacer(modifier = Modifier.height(UiDimens.xl))

            HorizontalDivider(
                color = colors.outlineVariant,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = UiDimens.xl)
            )

            SevenDayLoginCard()

            Spacer(modifier = Modifier.height(UiDimens.xl))

            HorizontalDivider(
                color = colors.outlineVariant,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = UiDimens.xl)
            )

            BadgeUpgradeCard()

            Spacer(modifier = Modifier.height(UiDimens.xl))

            HorizontalDivider(
                color = colors.outlineVariant,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = UiDimens.xl)
            )

            HelpTaskCard()
        }
    }
}//fin





@Composable
fun InviteFriendCard(inviteLink: String) {

    val colors = MaterialTheme.colorScheme

    val clipboard = rememberClipboardHelper()
    val share = rememberShareHelper()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.surface)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {






            Box(
                modifier = Modifier
                    .size(185.dp)

                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.invite),
                    contentDescription = "Arkadaşını Davet Et",
                    modifier = Modifier.fillMaxSize(0.9f)
                )
            }

            Spacer(modifier = Modifier.height(9.dp))


            Text(
                text = "Arkadaşını Davet Et",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colors.onSurface
            )
            Text(
                text = "50 çip kazan !",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {




                Row(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .border(
                            width = 1.dp,
                            color = colors.primary,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clip(RoundedCornerShape(50.dp))
                        .background(colors.primaryContainer),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = inviteLink,
                        fontSize = 14.sp,
                        color = colors.onPrimaryContainer,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp, end = 12.dp),
                        maxLines = 1
                    )


                    Button(
                        onClick = { clipboard.copy(inviteLink) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary, contentColor = colors.onPrimary
                        ),
                        shape = RoundedCornerShape(
                            topStart = 0.dp, bottomStart = 0.dp,
                            topEnd = 50.dp, bottomEnd = 50.dp
                        ),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                        modifier = Modifier.height(48.dp).offset(x = 10.dp)
                    ) {
                        Text(
                            text = "Kopyala",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))
                }


                Spacer(modifier = Modifier.width(8.dp))


                IconButton(
                    onClick = { share.shareText(inviteLink) },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.paylasicon),
                        contentDescription = "Paylaş",
                        tint = colors.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

        }




    }

    }


@Composable
fun SevenDayLoginCard() {

    val colors = MaterialTheme.colorScheme

    val orange = colors.primary





    val lightBg = colors.surface

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(lightBg)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.doyourduty),
            contentDescription = "Takvim İkonu",
            modifier = Modifier
                .size(140.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "Uygulamaya 7 gün boyunca giriş yap",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colors.onSurface
        )
        Text(
            text = "50 çip kazan !",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 1..4) {
                    DayCircle(
                        dayNumber = i,
                        isCompleted = i <= 2,
                        orange = orange
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 5..7) {
                    DayCircle(
                        dayNumber = i,
                        isCompleted = false,
                        orange = orange
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}


@Composable
fun DayCircle(
    dayNumber: Int,
    isCompleted: Boolean,
    orange: Color
) {

    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .width(60.dp)
            .height(70.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        Box(
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .background(if (isCompleted) orange else colors.surface)
                .border(2.dp, orange, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$dayNumber",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isCompleted) colors.onPrimary else colors.onSurface
            )
        }


        if (isCompleted) {
            Image(
                painter = painterResource(id = R.drawable.taskicontik),
                contentDescription = "Onay İşareti",
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = (-4).dp)
            )
        }
    }
}


@Composable
fun BadgeUpgradeCard() {

    val colors = MaterialTheme.colorScheme
    val orange = colors.primary
    val black = colors.onSurface
    val fluorange = colors.primaryContainer
    val lightBg = colors.surface

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(lightBg)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.lvlicon),
            contentDescription = "Rozet Atla",
            modifier = Modifier
                .size(140.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "Rozet atla",
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            color = colors.onSurface
        )
        Text(
            text = "50 çip kazan !",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.onelvl),
                contentDescription = "Bronz Rozet",
                modifier = Modifier.size(68.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                painter = painterResource(id = R.drawable.arrow_2),
                contentDescription = "Geçiş Oku",
                modifier = Modifier.size(58.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = R.drawable.threelvlflu),
                contentDescription = "Gümüş Rozet",
                modifier = Modifier.size(68.dp)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))


        OutlinedButton(
            onClick = {  },
            border = BorderStroke(1.dp, orange),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = fluorange ),
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 6.dp)
        ) {
           Icon(
                painter = painterResource(id = R.drawable.rzticon),
                contentDescription = "Rozet İkonu",
                tint = orange,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Rozetlere Göz At",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = black
            )
        }
    }
}



@Composable
fun HelpTaskCard() {

    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterResource(id = R.drawable.taskimage),
            contentDescription = "Yardım Et İkonu",
            modifier = Modifier.size(140.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))






        Spacer(modifier = Modifier.height(35.dp))
        SectionLabel("- TALEP -", size = 25f)
        Spacer(modifier = Modifier.height(1.dp))


        /*------  1 ------*/
        TaskCardState(
            title = "1 Talep Gerçekleştir",
            rewardText = "5 ÇİP KAZAN!",
            progressText = "0/100 Takas Tamamlandı",
            isActive = true,
            isCompleted = true
        )


        Spacer(modifier = Modifier.height(30.dp))
        BubblesPathDividerVertical()
        Spacer(modifier = Modifier.height(30.dp))



        /*------  2 ------*/
        TaskCardState(
            title = "5 Talep Gerçekleştir",
            rewardText = "15 ÇİP KAZAN!",
            progressText = "0/100 Takas Tamamlandı",
            isActive = true,
            isCompleted = true
        )


        Spacer(modifier = Modifier.height(30.dp))
        BubblesPathDividerVertical()
        Spacer(modifier = Modifier.height(30.dp))




        /*------  3 ------*/
        TaskCardState(
            title = "50 Talep Gerçekleştir",
            rewardText = "100 ÇİP KAZAN!",
            progressText = "0/100 Takas Tamamlandı",
            isActive = true,
            isCompleted = false
        )





        Spacer(modifier = Modifier.height(100.dp))
        SectionLabel("- YARDIM -", size = 25f)
        Spacer(modifier = Modifier.height(1.dp))



        /*------  4 ------*/
        TaskCardState(
            title = "1 Yardım Gerçekleştir",
            rewardText = "10 ÇİP KAZAN!",
            progressText = "0/100 Takas Tamamlandı",
            isActive = true,
            isCompleted = false
        )


        Spacer(modifier = Modifier.height(30.dp))
        BubblesPathDividerVertical()
        Spacer(modifier = Modifier.height(30.dp))



        /*------  5 ------*/
        TaskCardState(
            title = "5 Yardım Gerçekleştir",
            rewardText = "30 ÇİP KAZAN!",
            progressText = "0/100 Takas Tamamlandı",
            isActive = true,
            isCompleted = false
        )


        Spacer(modifier = Modifier.height(30.dp))
        BubblesPathDividerVertical()
        Spacer(modifier = Modifier.height(30.dp))



        /*------  6 ------*/
        TaskCardState(
            title = "50 Yardım Gerçekleştir",
            rewardText = "200 ÇİP KAZAN!",
            progressText = "0/100 Takas Tamamlandı",
            isActive = true,
            isCompleted = false
        )




        Spacer(modifier = Modifier.height(100.dp))
        SectionLabel("- TAKAS -", size = 25f)
        Spacer(modifier = Modifier.height(1.dp))



        /*------  7 ------*/
        TaskCardState(
            title = "1 Takas Gerçekleştir",
            rewardText = "5 ÇİP KAZAN!",
            progressText = "0/100 Takas Tamamlandı",
            isActive = true,
            isCompleted = false
        )


        Spacer(modifier = Modifier.height(30.dp))
        BubblesPathDividerVertical()
        Spacer(modifier = Modifier.height(30.dp))


        /*------  8 ------*/
        TaskCardState(
            title = "5 Takas Gerçekleştir",
            rewardText = "15 ÇİP KAZAN!",
            progressText = "0/100 Takas Tamamlandı",
            isActive = true,
            isCompleted = false
        )


        Spacer(modifier = Modifier.height(30.dp))
        BubblesPathDividerVertical()
        Spacer(modifier = Modifier.height(30.dp))







         /*------  9 ------*/
        TaskCardState(
             title = "50 Takas Gerçekleştir",
             rewardText = "100 ÇİP KAZAN!",
             progressText = "0/100 Takas Tamamlandı",
             isActive = true,
             isCompleted = false
        )



    }
}




/* active-passive automation */

@Composable
fun TaskCardState(
title: String,
rewardText: String,
progressText: String,
isActive: Boolean,
isCompleted: Boolean
) {

    val colors = MaterialTheme.colorScheme
    val orange = colors.primary
    val green = colors.tertiary
    val lightGray = colors.surfaceVariant
    val grayText = colors.onSurfaceVariant




val textColor = if (isActive) colors.onSurface else grayText
val rewardColor = if (isActive) colors.onSurface else grayText
val barColor = if (isActive) orange else lightGray
val alphaValue = if (isActive) 1f else 0.5f

Column(
horizontalAlignment = Alignment.CenterHorizontally,
modifier = Modifier
   .fillMaxWidth()
   .alpha(alphaValue)
   .padding(vertical = 10.dp)
) {

Text(
   text = title,
   fontSize = 18.sp,
   fontWeight = FontWeight.Medium,
   color = textColor
)


Text(
   text = rewardText,
   fontSize = 22.sp,
   fontWeight = FontWeight.Bold,
   color = rewardColor
)

Spacer(modifier = Modifier.height(12.dp))


Row(
   verticalAlignment = Alignment.CenterVertically,
   horizontalArrangement = Arrangement.Center,
   modifier = Modifier
       .fillMaxWidth(0.9f)
       .height(32.dp)
) {

   Box(
       modifier = Modifier
           .weight(1f)
           .height(12.dp)
           .clip(RoundedCornerShape(50.dp))
           .background(colors.surfaceVariant)
   ) {

       Box(
           modifier = Modifier
               .fillMaxWidth(if (isCompleted) 1f else 0.4f)
               .height(12.dp)
               .clip(RoundedCornerShape(50.dp))
               .background(barColor)
       )
   }

   Spacer(modifier = Modifier.width(6.dp))


   if (isActive && isCompleted) {
       Image(
           painter = painterResource(id = R.drawable.taskicontik),
           contentDescription = "Tamamlandı",
           modifier = Modifier.size(22.dp)
       )
   }
}

Spacer(modifier = Modifier.height(6.dp))


Text(
   text = progressText,
   fontSize = 13.sp,
   color = textColor
)

Spacer(modifier = Modifier.height(12.dp))


if (isActive && isCompleted) {
   Button(
       onClick = {  },
       colors = ButtonDefaults.buttonColors(
           containerColor = green,
           contentColor = colors.onTertiary
       ),
       shape = RoundedCornerShape(50.dp),
       modifier = Modifier
           .width(150.dp)
           .height(44.dp)
   ) {
       Text(
           text = "Ödülü Al",
           fontSize = 16.sp,
           fontWeight = FontWeight.Bold,
           color = colors.onTertiary
       )
   }
}
}
}



@Composable
fun SectionLabel(
    text: String = "YARDIM",
    size: Float = 32f
) {

    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = size.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onSurfaceVariant,
            maxLines = 1
        )
    }
}



@Composable
fun BubblesPathDividerVertical() {

    val colors = MaterialTheme.colorScheme

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        listOf(6.dp, 10.dp, 8.dp, 12.dp, 8.dp, 10.dp, 6.dp).forEachIndexed { index, size ->
            Box(
                modifier = Modifier
                    .size(size)
                    .clip(CircleShape)
                    .background(
                        if (index % 2 == 0)
                            colors.onSurfaceVariant.copy(alpha = 0.9f)
                        else
                            colors.surfaceVariant
                    )
            )
            if (index != 6) Spacer(modifier = Modifier.height(6.dp))
        }
    }
}











































