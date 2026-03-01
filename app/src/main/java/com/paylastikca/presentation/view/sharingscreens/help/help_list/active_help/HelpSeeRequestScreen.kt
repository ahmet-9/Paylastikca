package com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paylastikca.app.R
import com.paylastikca.presentation.view.common_ui.components.layout.PageScaffold
import com.paylastikca.presentation.view.common_ui.components.layout.rememberAdaptive
import com.paylastikca.presentation.view.common_ui.components.navigation.BackButton
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.RequestDecisionState
import com.paylastikca.presentation.view.common_ui.components.templates.sharingscreens_templates.RequestRevealTemplate

@Composable
fun HelpSeeRequestScreen(navController: NavController) {
    val colors = MaterialTheme.colorScheme

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(colors.primaryContainer)
    }

    val adaptive = rememberAdaptive()
    val scale = adaptive.scale

    var accepted by rememberSaveable { mutableStateOf(false) }

    PageScaffold(containerColor = colors.background) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()

                .then(
                    if (adaptive.maxContentWidth != null) {
                        Modifier
                            .widthIn(max = adaptive.maxContentWidth)
                    } else Modifier
                )
        ) {


            BackButton(
                title = "",
                modifier = Modifier
                    .background(colors.primaryContainer)
                    .height(54.dp),
                onBackClick = { navController.popBackStack() },
                scale = scale
            )

            val pendingState = RequestDecisionState.Pending(
                roleTitle = "ÖĞRENCİ",
                locationText = "KONYA/KADINHANI/KARAKAYA",
                distanceText = "3 km uzaklıkta",

                ratingText = "4.2/5",
                ratingValue = 4.2f,
                badgeText = "İlk Adım",
                badgeRes = R.drawable.lvl_one,
                interactionCountText = "25",
                interactionSubtitle = "adet gerçekleşti",

                itemTitle = "Yer halısı",
                itemDesc = "2.el temiz halı ... (uzun olabilir)",
                itemCategoryLabel = "KATEGORİ: Halı",
                itemImageRes = null,

                infoLines = listOf(
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "loremipsum dolar fhbuvfvhfy vbhvuyhbvuv",
                    "uzun metin gelirse burada kutu içi scroll yapacak",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnbıdsbv9nduıogbj9gfıujbg9uıbjhg9pubhgumdnovıkıdhgu9fvjkdoıfdguh9udfvojkdpofıdghufpfojdkp",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",
                    "Lorem ipsum dolarfjdonvgbjvgbougn bobnbobnbn gjib nubobnbonbnb",





                )
            )

            val acceptedState = RequestDecisionState.Accepted(
                fullName = "Ahmet Badem",
                avatarRes = R.drawable.usericon,
                infoLines = listOf(
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.dgnfhngjmjmjmgtgfhvjbfvbvgfvbhbhbshhvbyufvbuyshvbdyıvbsdycsvdbıusdyvbısyvbsuıvbsvıhbvysgvvvhhhhhhhhhhhhhhhhhhhhhh",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Güvenli buluşma alanlarını tercih edin.dgnfhngjmjmjmgtgfhvjbfvbvgfvbhbhbshhvbyufvbuyshvbdyıvbsdycsvdbıusdyvbısyvbsuıvbsvıhbvysgvvvhhhhhhhhhhhhhhhhhhhhhh",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Güvenli buluşma alanlarını tercih edin.dgnfhngjmjmjmgtgfhvjbfvbvgfvbhbhbshhvbyufvbuyshvbdyıvbsdycsvdbıusdyvbısyvbsuıvbsvıhbvysgvvvhhhhhhhhhhhhhhhhhhhhhh",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Güvenli buluşma alanlarını tercih edin.dgnfhngjmjmjmgtgfhvjbfvbvgfvbhbhbshhvbyufvbuyshvbdyıvbsdycsvdbıusdyvbısyvbsuıvbsvıhbvysgvvvhhhhhhhhhhhhhhhhhhhhhh",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Güvenli buluşma alanlarını tercih edin.dgnfhngjmjmjmgtgfhvjbfvbvgfvbhbhbshhvbyufvbuyshvbdyıvbsdycsvdbıusdyvbısyvbsuıvbsvıhbvysgvvvhhhhhhhhhhhhhhhhhhhhhh",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Güvenli buluşma alanlarını tercih edin.dgnfhngjmjmjmgtgfhvjbfvbvgfvbhbhbshhvbyufvbuyshvbdyıvbsdycsvdbıusdyvbısyvbsuıvbsvıhbvysgvvvhhhhhhhhhhhhhhhhhhhhhh",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Güvenli buluşma alanlarını tercih edin.dgnfhngjmjmjmgtgfhvjbfvbvgfvbhbhbshhvbyufvbuyshvbdyıvbsdycsvdbıusdyvbısyvbsuıvbsvıhbvysgvvvhhhhhhhhhhhhhhhhhhhhhh",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                    "Artık uygulama içi mesajlaşma başlatabilirsiniz.",
                    "Telefon numarası paylaşmak zorunda değilsiniz.",
                    "Güvenli buluşma alanlarını tercih edin.",
                )
            )

            RequestRevealTemplate(
                state = if (!accepted) pendingState else acceptedState,
                scale = scale,
                maxContentWidth = adaptive.maxContentWidth,
                horizontalPadding = adaptive.pageHorizontalPadding,

                onReject = { navController.popBackStack() },
                onApprove = { accepted = true },
                onContact = {

                }
            )
        }
    }
}