package com.paylastikca.presentation.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.paylastikca.presentation.view.homepage_screens.giftwheel.GiftWheelScreen
import com.paylastikca.presentation.view.homepage_screens.giftwheel.CongratulationsScreen
import com.paylastikca.presentation.view.homepage_screens.HomeMainScreen
import com.paylastikca.presentation.view.homepage_screens.ShoppingCentreScreen
import com.paylastikca.presentation.view.homepage_screens.TasksScreen
import com.paylastikca.presentation.view.loginscreens.ForgotYourPasswordScreen
import com.paylastikca.presentation.view.loginscreens.LogInScreen
import com.paylastikca.presentation.view.loginscreens.onboardingscreens.FifthScreen
import com.paylastikca.presentation.view.loginscreens.onboardingscreens.FirstScreen
import com.paylastikca.presentation.view.loginscreens.onboardingscreens.FourthScreen
import com.paylastikca.presentation.view.loginscreens.onboardingscreens.SecondScreen
import com.paylastikca.presentation.view.loginscreens.onboardingscreens.ThirdScreen
import com.paylastikca.presentation.view.loginscreens.SignUpScreen
import com.paylastikca.presentation.view.loginscreens.SnapSplash
import com.paylastikca.presentation.view.notificationsscreens.NotificationMainScreen
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.HelpandInformationMainScreens
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.AboutUs
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.ContactUs
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.HelpAndSss
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.PrivacyPolicy
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.TermsOfUse
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.PrivacySecurityMainScreen
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.DeleteAccountVerificationCode
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.active.VerificationActive
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.active.VerificationActiveRemove
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.active.VerificationRemoveChooseMethod
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.VerificationNoActive
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.VerificationChooseMethod

import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.VerificationCompleted
import com.paylastikca.presentation.view.profilescreens.application_preferences_screens.SelectLanguage
import com.paylastikca.presentation.view.profilescreens.application_preferences_screens.ApplicationPreferencesMain
import com.paylastikca.presentation.view.profilescreens.BadgessScreen
import com.paylastikca.presentation.view.profilescreens.NotificationSettingsScreen
import com.paylastikca.presentation.view.profilescreens.contact_information_screens.ContactInformationMain

import com.paylastikca.presentation.view.profilescreens.ProfileEditScreen
import com.paylastikca.presentation.view.profilescreens.ProfileMainScreen
import com.paylastikca.presentation.view.sharingscreens.SharingMainScreen
import com.paylastikca.presentation.view.sharingscreens.chat.DmBox
import com.paylastikca.presentation.view.sharingscreens.chat.DmBoxChat
import com.paylastikca.presentation.view.sharingscreens.help.HelpMyListActive
import com.paylastikca.presentation.view.sharingscreens.help.HelpMyListActiveDelete
import com.paylastikca.presentation.view.sharingscreens.help.HelpMyListActiveDeleteHelpDelete
import com.paylastikca.presentation.view.sharingscreens.help.HelpMainScreen
import com.paylastikca.presentation.view.sharingscreens.help.HelpMyListActiveSeeRequest
import com.paylastikca.presentation.view.sharingscreens.help.HelpMyListActiveSeeRequestGoToContact
import com.paylastikca.presentation.view.sharingscreens.help.HelpMyListOutdated
import com.paylastikca.presentation.view.sharingscreens.help.HelpMyListOutdatedGivePoints
import com.paylastikca.presentation.view.sharingscreens.help.HelpMyListOutdatedGivePointsPointsSend
import com.paylastikca.presentation.view.sharingscreens.help.HelpScreen
import com.paylastikca.presentation.view.sharingscreens.help.ThankYouScreen
import com.paylastikca.presentation.view.sharingscreens.request.RequestMainScreen
import com.paylastikca.presentation.view.sharingscreens.request.RequestMyListActive
import com.paylastikca.presentation.view.sharingscreens.request.RequestMyListActiveDelete
import com.paylastikca.presentation.view.sharingscreens.request.RequestMyListActiveDeleteRequestDelete
import com.paylastikca.presentation.view.sharingscreens.request.RequestMyListOutdated
import com.paylastikca.presentation.view.sharingscreens.request.RequestMyListOutdatedGivePoints
import com.paylastikca.presentation.view.sharingscreens.request.RequestMyListOutdatedGivePointsPointsSend
import com.paylastikca.presentation.view.sharingscreens.request.RequestScreenProductDetails
import com.paylastikca.presentation.view.sharingscreens.request.RequestScreenProductDetailsPhotoZoom
import com.paylastikca.presentation.view.sharingscreens.request.RequestScreenProductDetailsRequestSend
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListActiveDeleteSwapDelete
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMainScreen
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListActive
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListActiveDelete
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListActiveSeeRequest
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListActiveSeeRequestGoToContact
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListActiveSeeRequestPhotoZoom
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListOutdated
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListOutdatedGivePoints
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListOutdatedGivePointsPointsSend
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListSwapDiscovey
import com.paylastikca.presentation.view.sharingscreens.swap.SwapMyListSwapDiscoveyPhotoZoom
import com.paylastikca.presentation.view.sharingscreens.swap.SwapScreenNoResultsFound
import com.paylastikca.presentation.view.sharingscreens.swap.SwapScreenNoResultsFoundSwapMyListSwapDiscovery
import com.paylastikca.presentation.view.sharingscreens.swap.SwapScreenResultsFound
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.ChangePassword
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.DeleteAccount


@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "NotificationSettingsScreen" +
                "",
        modifier = Modifier.padding(innerPadding)
    ) {







        //homePageScreens
                //giftwheel
                composable("CongratulationsScreen") { CongratulationsScreen()  }
                composable("GiftWheelScreen") { GiftWheelScreen()  }
                //----

        composable("HomeMainScreen") { HomeMainScreen(navController)  }
        composable("ShoppingCentreScreen") { ShoppingCentreScreen(navController = navController)  }
        composable("TasksScreen") { TasksScreen(navController = navController)  }



        //loginScreens
                //onboardingScreens
                composable("FifthScreen") { FifthScreen(navController)  }
                composable("FirstScreen") { FirstScreen(navController)  }
                composable("FourthScreen") { FourthScreen(navController)  }
                composable("SecondScreen") { SecondScreen(navController)  }
                composable("ThirdScreen") { ThirdScreen(navController)  }
                //------

        composable("ForgotYourPasswordScreen") { ForgotYourPasswordScreen()  }
        composable("LogInScreen") { LogInScreen()  }
        composable("SignUpScreen") { SignUpScreen()  }
        composable("SnapSplash") { SnapSplash()  }

        //notificationsScreen

       composable("NotificationMainScreen") { NotificationMainScreen(navController)  }



        //profileScreen
                //applicationPreferencesScreens
                composable("ApplicationPreferencesMain") { ApplicationPreferencesMain()  }
                composable("SelectLanguage") { SelectLanguage()  }
                 //----

                //contactInformationScreens
                composable("ContactInformationMain") { ContactInformationMain()  }
              //  composable("dateOfBirth") { dateOfBirth()  }
                //composable("updateEmail") { updateEmail()  }
                //composable("updateEmailVerificationCode") { updateEmailVerificationCode()  }
//composable("updateNumber") { updateNumber()  }
               // composable("updateNumberVerificationCode") { updateNumberVerificationCode()  }
                //--

                //HelpandInformationScreens
                composable("AboutUs") { AboutUs()  }
                composable("ContactUs") { ContactUs()  }
                composable("HelpandInformationMainScreens") { HelpandInformationMainScreens()  }
                composable("HelpAndSss") { HelpAndSss()  }
                composable("PrivacyPolicy") { PrivacyPolicy()  }
                composable("TermsOfUse") { TermsOfUse()  }
                //---

                //PrivacySecurityScreens
        composable("VerificationRemoveChooseMethod") {
            VerificationRemoveChooseMethod(
                registeredEmail = "ornek@example.com",   // TODO: gerçek kullanıcı email
                registeredPhone = "+905551234567",       // TODO: gerçek kullanıcı telefon
                onBack = { navController.popBackStack() },
                onRemoveVerifiedSuccess = {
                    // TODO: burada gerçekten 2FA disable API çağıracaksın
                    navController.popBackStack() // istersen Active'e dön
                }
            )
        }

        composable("ChangePassword") {
            ChangePassword(
                onBack = { navController.popBackStack() },
                registeredEmail = "ornek@example.com",
                registeredPhone = "+905551234567",
                onPasswordChanged = { navController.popBackStack() }
            )
        }

        composable("DeleteAccount") {
            DeleteAccount(
                onBack = { navController.popBackStack() },
                onAccountDeleted = { /* TODO */ }
            )
        }




        composable("DeleteAccountVerificationCode") { DeleteAccountVerificationCode()  }
                composable("PrivacySecurityMainScreen") { PrivacySecurityMainScreen()  }
                composable("VerificationActive") { VerificationActive(navController)  }
        composable("VerificationActiveRemove") {
            VerificationActiveRemove(
                onCancel = { navController.popBackStack() },
                onConfirmRemove = {
                    navController.popBackStack() // popup route'unu kapat
                    navController.navigate("VerificationRemoveChooseMethod")
                }
            )
        }


        composable("VerificationNoActive") { VerificationNoActive()  }
                composable("VerificationChooseMethod") { VerificationChooseMethod()  }
               // composable("twoStepVerificationNoActiveChooseMethodMailSend") { twoStepVerificationNoActiveChooseMethodMailSend()  }
              //  composable("twoStepVerificationNoActiveChooseMethodMailSendVerificationCode") { twoStepVerificationNoActiveChooseMethodMailSendVerificationCode()  }
                composable("VerificationCompleted") { VerificationCompleted()  }
                //-----

        composable("BadgessScreen") { BadgessScreen(navController )  }
        composable("NotificationSettingsScreen") { NotificationSettingsScreen()  }
        composable("ProfileEditScreen") { ProfileEditScreen()  }
        composable("ProfileMainScreen") { ProfileMainScreen(navController)  }


        //sharingScreen
                  //chat
                   composable("DmBox") { DmBox()  }
                   composable("DmBoxChat") { DmBoxChat()  }
                  //---

                  //help
                   composable("HelpMainScreen") { HelpMainScreen()  }
                   composable("HelpMyListActive") { HelpMyListActive()  }
                   composable("HelpMyListActiveDelete") { HelpMyListActiveDelete()  }
                   composable("HelpMyListActiveDeleteHelpDelete") { HelpMyListActiveDeleteHelpDelete()  }
                   composable("HelpMyListActiveSeeRequest") { HelpMyListActiveSeeRequest()  }
                   composable("HelpMyListActiveSeeRequestGoToContact") { HelpMyListActiveSeeRequestGoToContact()  }
                   composable("HelpMyListOutdated") { HelpMyListOutdated()  }
                   composable("HelpMyListOutdatedGivePoints") { HelpMyListOutdatedGivePoints()  }
                   composable("HelpMyListOutdatedGivePointsPointsSend") { HelpMyListOutdatedGivePointsPointsSend()  }
                   composable("HelpScreen") { HelpScreen()  }
                   composable("ThankYouScreen") { ThankYouScreen()  }
                  //--

                   //request
                   composable("RequestMainScreen") { RequestMainScreen()  }
                   composable("RequestMyListActive") { RequestMyListActive()  }
                   composable("RequestMyListActiveDelete") { RequestMyListActiveDelete()  }
                   composable("RequestMyListActiveDeleteRequestDelete") { RequestMyListActiveDeleteRequestDelete()  }
                   composable("RequestMyListOutdated") { RequestMyListOutdated()  }
                   composable("RequestMyListOutdatedGivePoints") { RequestMyListOutdatedGivePoints()  }
                   composable("RequestMyListOutdatedGivePointsPointsSend") { RequestMyListOutdatedGivePointsPointsSend()  }
                   composable("RequestScreenProductDetails") { RequestScreenProductDetails()  }
                   composable("RequestScreenProductDetailsPhotoZoom") { RequestScreenProductDetailsPhotoZoom()  }
                   composable("RequestScreenProductDetailsRequestSend") { RequestScreenProductDetailsRequestSend()  }
                   //--

                    //swap
                    composable("SwapMainScreen") { SwapMainScreen()  }
                    composable("SwapMyListActive") { SwapMyListActive()  }
                    composable("SwapMyListActiveDelete") { SwapMyListActiveDelete()  }
                    composable("SwapMyListActiveDeleteSwapDelete") { SwapMyListActiveDeleteSwapDelete()  }
                    composable("SwapMyListActiveSeeRequest") { SwapMyListActiveSeeRequest()  }
                    composable("SwapMyListActiveSeeRequestGoToContact") { SwapMyListActiveSeeRequestGoToContact()  }
                    composable("SwapMyListActiveSeeRequestPhotoZoom") { SwapMyListActiveSeeRequestPhotoZoom()  }
                    composable("SwapMyListOutdated") { SwapMyListOutdated()  }
                    composable("SwapMyListOutdatedGivePoints") { SwapMyListOutdatedGivePoints()  }
                    composable("SwapMyListOutdatedGivePointsPointsSend") { SwapMyListOutdatedGivePointsPointsSend()  }
                    composable("SwapMyListSwapDiscovey") { SwapMyListSwapDiscovey()  }
                    composable("SwapMyListSwapDiscoveyPhotoZoom") { SwapMyListSwapDiscoveyPhotoZoom()  }
                    composable("SwapScreenNoResultsFound") { SwapScreenNoResultsFound()  }
                    composable("SwapScreenNoResultsFoundSwapMyListSwapDiscovery") { SwapScreenNoResultsFoundSwapMyListSwapDiscovery()  }
                    composable("SwapScreenResultsFound") { SwapScreenResultsFound()  }

                     //----


        composable("SharingMainScreen") { SharingMainScreen(navController)  }






    }
}