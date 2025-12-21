package com.paylastikca.presentation.view.profileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.paylastikca.app.R
import com.paylastikca.presentation.view.commonUI.components.PageHeader






@Composable
fun badgesScreen(
navController : NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PageHeader(
            title = "Rozetler",
            modifier = Modifier
                .padding(top = 24.dp, start = 0.dp)
                .fillMaxWidth(),
            onBackClick = { navController.popBackStack() }
        )

        HelpBadgesCard()
    }


}

@Composable
fun HelpBadgesCard() {
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




        Spacer(modifier = Modifier.height(20.dp))






        BadgesCardState(
            title = "İlk Adım",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_one,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
                    isActive = true,
            isCompleted = true
        )


        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))




        BadgesCardState(
            title = "Yardımcı",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n" +
                    " seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_two,
            badgeIconSize = 125,
            badgeIconOffsetY = 33,
            isActive = true,
            isCompleted = false
        )


        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))





        BadgesCardState(
            title = "Güvenilir",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n" +
                    " seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_three,
            badgeIconSize = 125,
            badgeIconOffsetY = 33,
            isActive = false,
            isCompleted = false
        )





        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(10.dp))




        BadgesCardState(
            title = "Kahraman",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n" +
                    " seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_four,
            badgeIconSize = 170,
            badgeIconOffsetY = 50,
            isActive = false,
            isCompleted = false
        )


        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))




        BadgesCardState(
            title = "Lider",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n" +
                    " seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_five,
            badgeIconSize = 125,
            badgeIconOffsetY = 33,
            isActive = false,
            isCompleted = false
        )


        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))




        BadgesCardState(
            title = "Usta",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n" +
                    " seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_six,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
            isActive = false,
            isCompleted = false
        )




        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))




        BadgesCardState(
            title = "Efsane",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n" +
                    " seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_seven,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
            isActive = false,
            isCompleted = false
        )


        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))



        BadgesCardState(
            title = "Elçi",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n" +
                    " seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_eight,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
            isActive = false,
            isCompleted = false
        )


        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))








        BadgesCardState(
            title = "Önder",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n" +
                    " seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_nine,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
                    isActive = false,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))








        BadgesCardState(
            title = "Altın",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n" +
                    " seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_ten,
            badgeIconSize = 125,
            badgeIconOffsetY = 32,
            isActive = false,
            isCompleted = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        BadgeDotsDividerVertical(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(30.dp))








        BadgesCardState(
            title = "Ruhlu",
            subtitleText ="“Kaliteli ve sorunsuz etkileşimlerle \n" +
                    " seviye atlarsın.”",
            rewardText = "50 ÇİP HEDİYE!",
            progressText = "",
            badgeIconRes = R.drawable.lvl_eleven,
            badgeIconSize = 225,
            badgeIconOffsetY = 79,
            isActive = false,
            isCompleted = false
        )



    }
}

@Composable
fun BadgesCardState(
    title: String,
    subtitleText: String = "",
    rewardText: String,
    progressText: String,
    isActive: Boolean,
    isCompleted: Boolean,
    badgeIconRes: Int? = null,
    badgeIconSize: Int = 92,
    badgeIconOffsetY: Int = 0
) {
    val orange = Color(0xFFE67E00)
    val lightGray = Color(0xFFE0E0E0)
    val grayText = Color(0xFFBDBDBD)

    val textColor = if (isActive) Color.Black else grayText
    val rewardColor = if (isActive) Color.Black else grayText
    val barColor = if (isActive) orange else lightGray
    val alphaValue = if (isActive) 1f else 0.5f

    val iconDp = badgeIconSize.dp
    val iconRadius = iconDp / 2


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
            .alpha(alphaValue),
        contentAlignment = Alignment.TopCenter
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(22.dp))
                .background(Color.White)
                .padding(
                    top = if (badgeIconRes != null) iconRadius + 12.dp else 18.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 18.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = textColor
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = subtitleText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = rewardColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = rewardText,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
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
        }


        if (badgeIconRes != null) {
            Image(
                painter = painterResource(id = badgeIconRes),
                contentDescription = "Rozet",
                modifier = Modifier
                    .size(iconDp)
                    .offset(y = -iconRadius + badgeIconOffsetY.dp)
            )
        }
    }
}

@Composable
fun BadgeDotsDividerVertical(
    modifier: Modifier = Modifier,
    dotSize: Dp = 9.dp,
    dotColor: Color = Color(0xFF9E9E9E),
    dotCount: Int = 4,
    spacing: Dp = 30.dp
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        repeat(dotCount) { index ->
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(dotColor)
            )
            if (index != dotCount - 1) {
                Spacer(modifier = Modifier.height(spacing))
            }
        }
    }
}






