package com.paylastikca.presentation.view.homepageScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paylastikca.app.R
import com.paylastikca.presentation.view.commonUI.components.PageHeader



@Composable
fun tasksScreen(navController: NavController) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PageHeader(
            title = "Görevler",
            modifier = Modifier
                .padding(top = 24.dp, start = 0.dp)
                .fillMaxWidth(),
            onBackClick = { navController.popBackStack() }
        )



        Spacer(modifier = Modifier.height(10.dp))
        InviteFriendCard()
        Spacer(modifier = Modifier.height(20.dp))


        Divider(
            color = Color(0xFFBDBDBD),
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)

        )
        SevenDayLoginCard()
        Spacer(modifier = Modifier.height(20.dp))


        Divider(
            color = Color(0xFFBDBDBD),
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)

        )
        BadgeUpgradeCard()
        Spacer(modifier = Modifier.height(20.dp))


        Divider(
            color = Color(0xFFBDBDBD),
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)

        )
        HelpTaskCard()







    }//fin
}//fin





@Composable
fun InviteFriendCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
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
                color = Color.Black
            )
            Text(
                text = "50 çip kazan !",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val orange = Color(0xFFE67E00)
                val cream = Color(0xFFFFF7ED)



                Row(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .border(
                            width = 1.dp,
                            color = orange,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clip(RoundedCornerShape(50.dp))
                        .background(cream),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "https://www.paylastik",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp, end = 12.dp),
                        maxLines = 1
                    )


                    Button(
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(containerColor = orange),
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
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))
                }


                Spacer(modifier = Modifier.width(8.dp))


                IconButton(
                    onClick = {  },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.paylasicon),
                        contentDescription = "Paylaş",
                        tint = orange,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

        }




    }

    }


@Composable
fun SevenDayLoginCard() {
    val orange = Color(0xFFE67E00)
    val green = Color(0xFF4CAF50)
    val lightBg = Color.White

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
            color = Color.Black
        )
        Text(
            text = "50 çip kazan !",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
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
                        orange = orange,
                        green = green
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
                        orange = orange,
                        green = green
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
    orange: Color,
    green: Color
) {
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
                .background(if (isCompleted) orange else Color.White)
                .border(2.dp, orange, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$dayNumber",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isCompleted) Color.White else Color.Black
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
    val orange = Color(0xFFE67E00)
    val black = Color(0xFF000000)
    val fluorange = Color(0xFFFFCD90)

    val lightBg = Color.White

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
            color = Color.Black
        )
        Text(
            text = "50 çip kazan !",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
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
            border = BorderStroke(1.dp, fluorange),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
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
    val orange = Color(0xFFE67E00)
    val green = Color(0xFF4CAF50)
    val gray = Color(0xFFE0E0E0)
    val lightGrayText = Color(0xFF9E9E9E)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
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
val orange = Color(0xFFE67E00)
val green = Color(0xFF4CAF50)
val lightGray = Color(0xFFE0E0E0)
val grayText = Color(0xFFBDBDBD)


val textColor = if (isActive) Color.Black else grayText
val rewardColor = if (isActive) Color.Black else grayText
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
           .background(Color(0xFFE0E0E0))
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
           contentColor = Color.White
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
           color = Color.White
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
            color = Color(0xFFBDBDBD),
            maxLines = 1
        )
    }
}



@Composable
fun BubblesPathDividerVertical() {
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
                            Color(0xFF000000).copy(alpha = 0.9f)
                        else
                            Color(0xFFD9D9D9)
                    )
            )
            if (index != 6) Spacer(modifier = Modifier.height(6.dp))
        }
    }
}


@Composable
fun SectionLabel(text: String = "YARDIM") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = Color(0xFF9E9E9E),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}








































