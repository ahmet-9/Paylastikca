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
import com.paylastikca.presentation.view.sharingscreens.chat.DmBoxScreen
import com.paylastikca.presentation.view.sharingscreens.chat.DmBoxChatScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help.DeleteHelpScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help.HelpDeletionCompletedScreen
import com.paylastikca.presentation.view.sharingscreens.help.submit_help.SubmitHelpScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help.HelpSeeRequestScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help.HelpGoToContactScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.help_history.HelpHistoryListScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.help_history.HelpGivePointsScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.help_history.HelpPointsGivenCompletedScreen
import com.paylastikca.presentation.view.sharingscreens.help.submit_help.HelpThankYouScreen
import com.paylastikca.presentation.view.sharingscreens.request.submit_request.RequestResultListScreen
import com.paylastikca.presentation.view.sharingscreens.request.request_list.active_request.RequestActiveListScreen
import com.paylastikca.presentation.view.sharingscreens.request.request_list.active_request.DeleteRequestScreen
import com.paylastikca.presentation.view.sharingscreens.request.request_list.active_request.RequestDeletionCompletedScreen
import com.paylastikca.presentation.view.sharingscreens.request.request_list.request_history.RequestHistoryListScreen
import com.paylastikca.presentation.view.sharingscreens.request.request_list.request_history.RequestGivePointsScreen
import com.paylastikca.presentation.view.sharingscreens.request.request_list.request_history.RequestPointsGivenCompletedScreen
import com.paylastikca.presentation.view.sharingscreens.request.submit_request.RequestProductDetailScreen
import com.paylastikca.presentation.view.sharingscreens.request.submit_request.SendProductRequestScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.swap_active.SwapDeletionCompletedScreen
import com.paylastikca.presentation.view.sharingscreens.swap.submit_swap.SubmitSwapScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.swap_active.SwapActiveListScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.swap_active.DeleteSwapScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.swap_active.SwapSeeRequestScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.swap_active.SwapGoToContactScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.history_swap.SwapHistoryListScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.history_swap.SwapGivePointsScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.history_swap.SwapPointsGivenCompletedScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.swap_discovery.SwapDiscoveryListScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.swap_discovery.SwapProductDetailScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.swap_discovery.SwapNoResultsFoundScreen
import com.paylastikca.presentation.view.sharingscreens.swap.submit_swap.SwapResultsFoundScreen
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.ChangePassword
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.DeleteAccount
import com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help.HelpActiveListScreen
import com.paylastikca.presentation.view.sharingscreens.request.request_list.active_request.RequestGoToContactScreen
import com.paylastikca.presentation.view.sharingscreens.request.submit_request.RequestSearchScreen
import com.paylastikca.presentation.view.sharingscreens.request.submit_request.RequestSubmissionSuccessScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.swap_discovery.SwapSentRequestsScreen


@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "SharingMainScreen" +
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
                composable("ApplicationPreferencesMain") { ApplicationPreferencesMain(navController)  }
        composable(
            route = "selectLanguage/{currentCode}",
            arguments = listOf(
                androidx.navigation.navArgument("currentCode") {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) { backStackEntry ->

            val currentCode = backStackEntry.arguments?.getString("currentCode") ?: "tr"

            SelectLanguage(
                currentLanguageCode = currentCode,
                onLanguageSelected = { lang ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("selectedLanguageCode", lang.code)

                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("selectedLanguageLabel", lang.label)

                    navController.popBackStack()
                }
                ,
                onBack = { navController.popBackStack() }
            )
        }


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
                   composable("DmBoxScreen") { DmBoxScreen()  }
                   composable("DmBoxChatScreen") { DmBoxChatScreen()  }
                  //---

                  //help
                   composable("SubmitHelpScreen") { SubmitHelpScreen()  }
                   composable("HelpActiveListScreen") { HelpActiveListScreen()  }
                   composable("DeleteHelpScreen") { DeleteHelpScreen()  }
                   composable("HelpDeletionCompletedScreen") { HelpDeletionCompletedScreen()  }
                   composable("HelpSeeRequestScreen") { HelpSeeRequestScreen()  }
                   composable("HelpGoToContactScreen") { HelpGoToContactScreen()  }
                   composable("HelpHistoryListScreen") { HelpHistoryListScreen()  }
                   composable("HelpGivePointsScreen") { HelpGivePointsScreen()  }
                   composable("HelpPointsGivenCompletedScreen") { HelpPointsGivenCompletedScreen()  }
                   composable("HelpThankYouScreen") { HelpThankYouScreen()  }
                  //--

                   //request
                   composable("RequestSearchScreen") { RequestSearchScreen()  }
                   composable("RequestResultListScreen") { RequestResultListScreen()  }
                   composable("RequestActiveListScreen") { RequestActiveListScreen() }
                   composable("DeleteRequestScreen") { DeleteRequestScreen()  }
                   composable("RequestDeletionCompletedScreen") { RequestDeletionCompletedScreen()  }
                   composable("RequestHistoryListScreen") { RequestHistoryListScreen()  }
                   composable("RequestGivePointsScreen") { RequestGivePointsScreen()  }
                   composable("RequestPointsGivenCompletedScreen") { RequestPointsGivenCompletedScreen()  }
                   composable("RequestProductDetailScreen") { RequestProductDetailScreen()  }
                   composable("SendProductRequestScreen") { SendProductRequestScreen()  }
                   composable("RequestSubmissionSuccessScreen") { RequestSubmissionSuccessScreen()  }
                   composable("RequestGoToContactScreen") { RequestGoToContactScreen()  }

                   //--

                    //swap
                    composable("SubmitSwapScreen") { SubmitSwapScreen()  }
                    composable("SwapActiveListScreen") { SwapActiveListScreen()  }
                    composable("DeleteSwapScreen") { DeleteSwapScreen()  }
                    composable("SwapDeletionCompletedScreen") { SwapDeletionCompletedScreen()  }
                    composable("SwapSeeRequestScreen") { SwapSeeRequestScreen()  }
                    composable("SwapGoToContactScreen") { SwapGoToContactScreen()  }
                    composable("SwapHistoryListScreen") { SwapHistoryListScreen()  }
                    composable("SwapGivePointsScreen") { SwapGivePointsScreen()  }
                    composable("SwapPointsGivenCompletedScreen") { SwapPointsGivenCompletedScreen()  }
                    composable("SwapDiscoveryListScreen") { SwapDiscoveryListScreen()  }
                    composable("SwapProductDetailScreen") { SwapProductDetailScreen()  }
                    composable("SwapNoResultsFoundScreen") { SwapNoResultsFoundScreen()  }
                    composable("SwapResultsFoundScreen") { SwapResultsFoundScreen()  }
                    composable("SwapSentRequestsScreen") { SwapSentRequestsScreen()  }

                     //----


        composable("SharingMainScreen") { SharingMainScreen(navController)  }






    }
}