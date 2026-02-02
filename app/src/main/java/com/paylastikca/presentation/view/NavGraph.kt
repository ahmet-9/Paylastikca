package com.paylastikca.presentation.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.paylastikca.presentation.view.homepageScreen.giftWheel.GiftWheelScreen
import com.paylastikca.presentation.view.homepageScreen.giftWheel.congratulationsScreen
import com.paylastikca.presentation.view.homepageScreen.homePageMainScreen
import com.paylastikca.presentation.view.homepageScreen.shoppingCentreScreen
import com.paylastikca.presentation.view.homepageScreen.tasksScreen
import com.paylastikca.presentation.view.loginScreens.ForgotYourPasswordScreen
import com.paylastikca.presentation.view.loginScreens.logInScreen
import com.paylastikca.presentation.view.loginScreens.onboardingScreens.fifthScreen
import com.paylastikca.presentation.view.loginScreens.onboardingScreens.firstScreen
import com.paylastikca.presentation.view.loginScreens.onboardingScreens.fourthScreen
import com.paylastikca.presentation.view.loginScreens.onboardingScreens.secondScreen
import com.paylastikca.presentation.view.loginScreens.onboardingScreens.thirdScreen
import com.paylastikca.presentation.view.loginScreens.signUpScreen
import com.paylastikca.presentation.view.loginScreens.snapSplash
import com.paylastikca.presentation.view.notificationsScreen.notificationMainScreen
import com.paylastikca.presentation.view.profileScreen.HelpandInformationScreens.HelpandInformationMAİNScreens
import com.paylastikca.presentation.view.profileScreen.HelpandInformationScreens.aboutUs
import com.paylastikca.presentation.view.profileScreen.HelpandInformationScreens.contactUs
import com.paylastikca.presentation.view.profileScreen.HelpandInformationScreens.helpAndSSS
import com.paylastikca.presentation.view.profileScreen.HelpandInformationScreens.privacyPolicy
import com.paylastikca.presentation.view.profileScreen.HelpandInformationScreens.termsOfUse
import com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.PrivacySecurityMainScreen
import com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.changePassword
import com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.deleteAccount
import com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.deleteAccountVerificationCode
import com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.TwoStepVerification.Active.VerificationActive
import com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.TwoStepVerification.Active.VerificationActiveRemove
import com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.TwoStepVerification.Active.VerificationRemoveChooseMethod
import com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.TwoStepVerification.NoActive.VerificationNoActive
import com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.TwoStepVerification.NoActive.VerificationChooseMethod

import com.paylastikca.presentation.view.profileScreen.PrivacySecurityScreens.TwoStepVerification.NoActive.VerificationCompleted
import com.paylastikca.presentation.view.profileScreen.applicationPreferencesScreens.SelectLanguage
import com.paylastikca.presentation.view.profileScreen.applicationPreferencesScreens.applicationPreferencesMAİN
import com.paylastikca.presentation.view.profileScreen.badgesScreen
import com.paylastikca.presentation.view.profileScreen.contactInformationScreens.contactInformationMain

import com.paylastikca.presentation.view.profileScreen.notificationSettingsScreen
import com.paylastikca.presentation.view.profileScreen.profileEditScreen
import com.paylastikca.presentation.view.profileScreen.profileMainScreen
import com.paylastikca.presentation.view.sharingScreen.SharingMAİNScreen
import com.paylastikca.presentation.view.sharingScreen.chat.DmBox
import com.paylastikca.presentation.view.sharingScreen.chat.DmBoxChat
import com.paylastikca.presentation.view.sharingScreen.help.HelpMyListActive
import com.paylastikca.presentation.view.sharingScreen.help.HelpMyListActiveDelete
import com.paylastikca.presentation.view.sharingScreen.help.HelpMyListActiveDeletehelpDelete
import com.paylastikca.presentation.view.sharingScreen.help.helpMAİNScreen
import com.paylastikca.presentation.view.sharingScreen.help.helpMyListActiveseeRequest
import com.paylastikca.presentation.view.sharingScreen.help.helpMyListActiveseeRequestGoToContact
import com.paylastikca.presentation.view.sharingScreen.help.helpMyListOutdated
import com.paylastikca.presentation.view.sharingScreen.help.helpMyListOutdatedGivePoints
import com.paylastikca.presentation.view.sharingScreen.help.helpMyListOutdatedGivePointsPointsSend
import com.paylastikca.presentation.view.sharingScreen.help.helpScreen
import com.paylastikca.presentation.view.sharingScreen.help.thankYouScreen
import com.paylastikca.presentation.view.sharingScreen.request.requestMAİNScreen
import com.paylastikca.presentation.view.sharingScreen.request.requestMyListActive
import com.paylastikca.presentation.view.sharingScreen.request.requestMyListActiveDelete
import com.paylastikca.presentation.view.sharingScreen.request.requestMyListActiveDeleteRequestDelete
import com.paylastikca.presentation.view.sharingScreen.request.requestMyListOutdated
import com.paylastikca.presentation.view.sharingScreen.request.requestMyListOutdatedGivePoints
import com.paylastikca.presentation.view.sharingScreen.request.requestMyListOutdatedGivePointsPointsSend
import com.paylastikca.presentation.view.sharingScreen.request.requestScreenProductDetails
import com.paylastikca.presentation.view.sharingScreen.request.requestScreenProductDetailsPhotoZoom
import com.paylastikca.presentation.view.sharingScreen.request.requestScreenProductDetailsRequestSend
import com.paylastikca.presentation.view.sharingScreen.swap.SwapMyListActiveDeleteSwapDelete
import com.paylastikca.presentation.view.sharingScreen.swap.swapMAİNScreen
import com.paylastikca.presentation.view.sharingScreen.swap.swapMyListActive
import com.paylastikca.presentation.view.sharingScreen.swap.swapMyListActiveDelete
import com.paylastikca.presentation.view.sharingScreen.swap.swapMyListActiveSeeRequest
import com.paylastikca.presentation.view.sharingScreen.swap.swapMyListActiveSeeRequestGoToContact
import com.paylastikca.presentation.view.sharingScreen.swap.swapMyListActiveSeeRequestPhotoZoom
import com.paylastikca.presentation.view.sharingScreen.swap.swapMyListOutdated
import com.paylastikca.presentation.view.sharingScreen.swap.swapMyListOutdatedGivePoints
import com.paylastikca.presentation.view.sharingScreen.swap.swapMyListOutdatedGivePointsPointsSend
import com.paylastikca.presentation.view.sharingScreen.swap.swapMyListSwapDiscovey
import com.paylastikca.presentation.view.sharingScreen.swap.swapMyListSwapDiscoveyPhotoZoom
import com.paylastikca.presentation.view.sharingScreen.swap.swapScreenNoResultsFound
import com.paylastikca.presentation.view.sharingScreen.swap.swapScreenNoResultsFoundSwapMyListSwapDiscovery
import com.paylastikca.presentation.view.sharingScreen.swap.swapScreenResultsFound


@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "VerificationActive" +
                "",
        modifier = Modifier.padding(innerPadding)
    ) {







        //homePageScreens
                //giftwheel
                composable("congratulationsScreen") { congratulationsScreen()  }
                composable("giftwheelScreen") { GiftWheelScreen()  }
                //----

        composable("homePageMainScreen") { homePageMainScreen(navController)  }
        composable("shoppingCentreScreen") { shoppingCentreScreen(navController = navController)  }
        composable("tasksScreen") { tasksScreen(navController = navController)  }



        //loginScreens
                //onboardingScreens
                composable("fifthScreen") { fifthScreen(navController)  }
                composable("firstScreen") { firstScreen(navController)  }
                composable("fourthScreen") { fourthScreen(navController)  }
                composable("secondScreen") { secondScreen(navController)  }
                composable("thirdScreen") { thirdScreen(navController)  }
                //------

        composable("ForgotYourPasswordScreen") { ForgotYourPasswordScreen()  }
        composable("logInScreen") { logInScreen()  }
        composable("signUpScreen") { signUpScreen()  }
        composable("snapSplash") { snapSplash()  }

        //notificationsScreen

       composable("notificationMainScreen") { notificationMainScreen(navController)  }



        //profileScreen
                //applicationPreferencesScreens
                composable("applicationPreferencesMAİN") { applicationPreferencesMAİN()  }
                composable("SelectLanguage") { SelectLanguage()  }
                 //----

                //contactInformationScreens
                composable("contactInformationMain") { contactInformationMain()  }
              //  composable("dateOfBirth") { dateOfBirth()  }
                //composable("updateEmail") { updateEmail()  }
                //composable("updateEmailVerificationCode") { updateEmailVerificationCode()  }
//composable("updateNumber") { updateNumber()  }
               // composable("updateNumberVerificationCode") { updateNumberVerificationCode()  }
                //--

                //HelpandInformationScreens
                composable("aboutUs") { aboutUs()  }
                composable("contactUs") { contactUs()  }
                composable("HelpandInformationMAİNScreens") { HelpandInformationMAİNScreens()  }
                composable("helpAndSSS") { helpAndSSS()  }
                composable("privacyPolicy") { privacyPolicy()  }
                composable("termsOfUse") { termsOfUse()  }
                //---

                //PrivacySecurityScreens
        composable("verificationRemoveChooseMethod") {
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

        composable("changePassword") { changePassword()  }
                composable("deleteAccount") { deleteAccount()  }
                composable("deleteAccountVerificationCode") { deleteAccountVerificationCode()  }
                composable("PrivacySecurityMainScreen") { PrivacySecurityMainScreen()  }
                composable("VerificationActive") { VerificationActive(navController)  }
        composable("verificationActiveRemove") {
            VerificationActiveRemove(
                onCancel = { navController.popBackStack() },
                onConfirmRemove = {
                    navController.popBackStack() // popup route'unu kapat
                    navController.navigate("verificationRemoveChooseMethod")
                }
            )
        }


        composable("VerificationNoActive") { VerificationNoActive()  }
                composable("VerificationChooseMethod") { VerificationChooseMethod()  }
               // composable("twoStepVerificationNoActiveChooseMethodMailSend") { twoStepVerificationNoActiveChooseMethodMailSend()  }
              //  composable("twoStepVerificationNoActiveChooseMethodMailSendVerificationCode") { twoStepVerificationNoActiveChooseMethodMailSendVerificationCode()  }
                composable("VerificationCompleted") { VerificationCompleted()  }
                //-----

        composable("badgesScreen") { badgesScreen(navController )  }
        composable("notificationSettingsScreen") { notificationSettingsScreen()  }
        composable("profileEditScreen") { profileEditScreen()  }
        composable("profileMainScreen") { profileMainScreen(navController)  }


        //sharingScreen
                  //chat
                   composable("DmBox") { DmBox()  }
                   composable("DmBoxChat") { DmBoxChat()  }
                  //---

                  //help
                   composable("helpMAİNScreen") { helpMAİNScreen()  }
                   composable("HelpMyListActive") { HelpMyListActive()  }
                   composable("HelpMyListActiveDelete") { HelpMyListActiveDelete()  }
                   composable("HelpMyListActiveDeletehelpDelete") { HelpMyListActiveDeletehelpDelete()  }
                   composable("helpMyListActiveseeRequest") { helpMyListActiveseeRequest()  }
                   composable("helpMyListActiveseeRequestGoToContact") { helpMyListActiveseeRequestGoToContact()  }
                   composable("helpMyListOutdated") { helpMyListOutdated()  }
                   composable("helpMyListOutdatedGivePoints") { helpMyListOutdatedGivePoints()  }
                   composable("helpMyListOutdatedGivePointsPointsSend") { helpMyListOutdatedGivePointsPointsSend()  }
                   composable("helpScreen") { helpScreen()  }
                   composable("thankYouScreen") { thankYouScreen()  }
                  //--

                   //request
                   composable("requestMAİNScreen") { requestMAİNScreen()  }
                   composable("requestMyListActive") { requestMyListActive()  }
                   composable("requestMyListActiveDelete") { requestMyListActiveDelete()  }
                   composable("requestMyListActiveDeleteRequestDelete") { requestMyListActiveDeleteRequestDelete()  }
                   composable("requestMyListOutdated") { requestMyListOutdated()  }
                   composable("requestMyListOutdatedGivePoints") { requestMyListOutdatedGivePoints()  }
                   composable("requestMyListOutdatedGivePointsPointsSend") { requestMyListOutdatedGivePointsPointsSend()  }
                   composable("requestScreenProductDetails") { requestScreenProductDetails()  }
                   composable("requestScreenProductDetailsPhotoZoom") { requestScreenProductDetailsPhotoZoom()  }
                   composable("requestScreenProductDetailsRequestSend") { requestScreenProductDetailsRequestSend()  }
                   //--

                    //swap
                    composable("swapMAİNScreen") { swapMAİNScreen()  }
                    composable("swapMyListActive") { swapMyListActive()  }
                    composable("swapMyListActiveDelete") { swapMyListActiveDelete()  }
                    composable("SwapMyListActiveDeleteSwapDelete") { SwapMyListActiveDeleteSwapDelete()  }
                    composable("swapMyListActiveSeeRequest") { swapMyListActiveSeeRequest()  }
                    composable("swapMyListActiveSeeRequestGoToContact") { swapMyListActiveSeeRequestGoToContact()  }
                    composable("swapMyListActiveSeeRequestPhotoZoom") { swapMyListActiveSeeRequestPhotoZoom()  }
                    composable("swapMyListOutdated") { swapMyListOutdated()  }
                    composable("swapMyListOutdatedGivePoints") { swapMyListOutdatedGivePoints()  }
                    composable("swapMyListOutdatedGivePointsPointsSend") { swapMyListOutdatedGivePointsPointsSend()  }
                    composable("swapMyListSwapDiscovey") { swapMyListSwapDiscovey()  }
                    composable("swapMyListSwapDiscoveyPhotoZoom") { swapMyListSwapDiscoveyPhotoZoom()  }
                    composable("swapScreenNoResultsFound") { swapScreenNoResultsFound()  }
                    composable("swapScreenNoResultsFoundSwapMyListSwapDiscovery") { swapScreenNoResultsFoundSwapMyListSwapDiscovery()  }
                    composable("swapScreenResultsFound") { swapScreenResultsFound()  }

                     //----


        composable("SharingMAİNScreen") { SharingMAİNScreen(navController)  }






    }
}