package com.paylastikca.presentation.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

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
import com.paylastikca.presentation.view.loginscreens.SnapSplashScreen
import com.paylastikca.presentation.view.notificationsscreens.NotificationMainScreen
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.HelpandInformationMainScreens
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.AboutUs
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.ContactUs
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.HelpAndSss
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.PrivacyPolicy
import com.paylastikca.presentation.view.profilescreens.help_and_information_screens.TermsOfUse
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.PrivacySecurityMainScreen
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.active.VerificationActiveScreen
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.active.VerificationActiveRemoveScreen
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.VerificationNoActiveScreen
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.VerificationChooseMethodScreen

import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.no_active.VerificationCompletedScreen
import com.paylastikca.presentation.view.profilescreens.application_preferences_screens.SelectLanguageScreen
import com.paylastikca.presentation.view.profilescreens.application_preferences_screens.ApplicationPreferencesMainScreen
import com.paylastikca.presentation.view.profilescreens.BadgessScreen
import com.paylastikca.presentation.view.profilescreens.NotificationSettingsScreen
import com.paylastikca.presentation.view.profilescreens.contact_information_screens.ContactInformationMainScreen

import com.paylastikca.presentation.view.profilescreens.ProfileEditScreen
import com.paylastikca.presentation.view.profilescreens.ProfileMainScreen
import com.paylastikca.presentation.view.sharingscreens.SharingMainScreen
import com.paylastikca.presentation.view.sharingscreens.chat.DmBoxScreen
import com.paylastikca.presentation.view.sharingscreens.chat.DmBoxChatScreen

import com.paylastikca.presentation.view.sharingscreens.help.submit_help.SubmitHelpScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.help_history.HelpHistoryListScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.help_history.HelpGivePointsScreen
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
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.ChangePasswordScreen
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.DeleteAccountScreen
import com.paylastikca.presentation.view.profilescreens.privacy_security_screens.two_step_verification.active.VerificationRemoveChooseMethodScreen
import com.paylastikca.presentation.view.sharingscreens.chat.ReportTargetUi
import com.paylastikca.presentation.view.sharingscreens.chat.ReportUserScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help.HelpActiveListScreen
import com.paylastikca.presentation.view.sharingscreens.help.help_list.active_help.HelpSeeRequestScreen
import com.paylastikca.presentation.view.sharingscreens.request.request_list.active_request.RequestGoToContactScreen
import com.paylastikca.presentation.view.sharingscreens.request.submit_request.RequestSearchScreen
import com.paylastikca.presentation.view.sharingscreens.request.submit_request.RequestSubmissionSuccessScreen
import com.paylastikca.presentation.view.sharingscreens.swap.submit_swap.SubmitSwapScreen
import com.paylastikca.presentation.view.sharingscreens.swap.swap_list.swap_discovery.SwapSentRequestsScreen


@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "HelpActiveListScreen" +
                "",
        modifier = Modifier.padding(innerPadding)
    ) {


        //homePageScreens
        //giftwheel
        composable("CongratulationsScreen") { CongratulationsScreen() }
        composable("GiftWheelScreen") { GiftWheelScreen() }
        //----

        composable("HomeMainScreen") { HomeMainScreen(navController) }
        composable("ShoppingCentreScreen") { ShoppingCentreScreen(navController = navController) }
        composable("TasksScreen") { TasksScreen(navController = navController) }


        //loginScreens
        //onboardingScreens
        composable("FifthScreen") { FifthScreen(navController) }
        composable("FirstScreen") { FirstScreen(navController) }
        composable("FourthScreen") { FourthScreen(navController) }
        composable("SecondScreen") { SecondScreen(navController) }
        composable("ThirdScreen") { ThirdScreen(navController) }
        //------

        composable("ForgotYourPasswordScreen") { ForgotYourPasswordScreen() }
        composable("LogInScreen") { LogInScreen() }
        composable("SignUpScreen") { SignUpScreen() }
        composable("SnapSplashScreen") { SnapSplashScreen() }

        //notificationsScreen

        composable("NotificationMainScreen") { NotificationMainScreen(navController) }


        //profileScreen
        //applicationPreferencesScreens
        composable("ApplicationPreferencesMainScreen") {
            ApplicationPreferencesMainScreen(
                navController
            )
        }
        composable(
            route = "selectLanguage/{currentCode}",
            arguments = listOf(
                androidx.navigation.navArgument("currentCode") {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) { backStackEntry ->

            val currentCode = backStackEntry.arguments?.getString("currentCode") ?: "tr"

            SelectLanguageScreen(
                currentLanguageCode = currentCode,
                onLanguageSelected = { lang ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("selectedLanguageCode", lang.code)

                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("selectedLanguageLabel", lang.label)

                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }


        //----

        //contactInformationScreens
        composable("ContactInformationMainScreen") { ContactInformationMainScreen() }
        //  composable("dateOfBirth") { dateOfBirth()  }
        //composable("updateEmail") { updateEmail()  }
        //composable("updateEmailVerificationCode") { updateEmailVerificationCode()  }
//composable("updateNumber") { updateNumber()  }
        // composable("updateNumberVerificationCode") { updateNumberVerificationCode()  }
        //--

        //HelpandInformationScreens
        composable("AboutUs") { AboutUs() }
        composable("ContactUs") { ContactUs() }
        composable("HelpandInformationMainScreens") { HelpandInformationMainScreens() }
        composable("HelpAndSss") { HelpAndSss() }
        composable("PrivacyPolicy") { PrivacyPolicy() }
        composable("TermsOfUse") { TermsOfUse() }
        //---

        //PrivacySecurityScreens
        composable("VerificationRemoveChooseMethodScreen") {
            VerificationRemoveChooseMethodScreen(
                registeredEmail = "ornek@example.com",   // TODO: gerçek kullanıcı email
                registeredPhone = "+905551234567",       // TODO: gerçek kullanıcı telefon
                onBack = { navController.popBackStack() },
                onRemoveVerifiedSuccess = {
                    // TODO: burada gerçekten 2FA disable API çağıracaksın
                    navController.popBackStack() // istersen Active'e dön
                }
            )
        }

        composable("ChangePasswordScreen") {
            ChangePasswordScreen(
                onBack = { navController.popBackStack() },
                registeredEmail = "ornek@example.com",
                registeredPhone = "+905551234567",
                onPasswordChanged = { navController.popBackStack() }
            )
        }

        composable("DeleteAccountScreen") {
            DeleteAccountScreen(
                onBack = { navController.popBackStack() },
                onAccountDeleted = { /* TODO */ }
            )
        }




        composable("PrivacySecurityMainScreen") { PrivacySecurityMainScreen() }
        composable("VerificationActiveScreen") { VerificationActiveScreen(navController) }
        composable("VerificationActiveRemoveScreen") {
            VerificationActiveRemoveScreen(
                onCancel = { navController.popBackStack() },
                onConfirmRemove = {
                    navController.popBackStack() // popup route'unu kapat
                    navController.navigate("VerificationRemoveChooseMethodScreen")
                }
            )
        }


        composable("VerificationNoActiveScreen") { VerificationNoActiveScreen() }
        composable("VerificationChooseMethodScreen") { VerificationChooseMethodScreen() }
        // composable("twoStepVerificationNoActiveChooseMethodMailSend") { twoStepVerificationNoActiveChooseMethodMailSend()  }
        //  composable("twoStepVerificationNoActiveChooseMethodMailSendVerificationCode") { twoStepVerificationNoActiveChooseMethodMailSendVerificationCode()  }
        composable("VerificationCompletedScreen") { VerificationCompletedScreen() }
        //-----

        composable("BadgessScreen") { BadgessScreen(navController) }
        composable("NotificationSettingsScreen") { NotificationSettingsScreen() }
        composable("ProfileEditScreen") { ProfileEditScreen() }
        composable("ProfileMainScreen") { ProfileMainScreen(navController) }


        //sharingScreen
        //chat
        composable(route = "DmBoxScreen") {
            DmBoxScreen(navController)
        }

        composable(
            route = "DmBoxChatScreen/{chatId}",
            arguments = listOf(
                navArgument("chatId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId").orEmpty()
            DmBoxChatScreen(navController = navController, chatId = chatId)
        }

        composable(
            route = "ReportUserScreen/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId").orEmpty()

            ReportUserScreen(
                navController = navController,
                target = ReportTargetUi(
                    userId = userId,
                    name = "Ahmet Badem",
                    subtitle = "Sohbet üzerinden"
                )
            )
        }





        //---

                  //help
                   composable("SubmitHelpScreen") { SubmitHelpScreen(navController)  }
                   composable("HelpActiveListScreen") { HelpActiveListScreen(navController)  }
                 //  composable("DeleteHelpScreen") { DeleteHelpScreen(navController)  }
                 //  composable("HelpDeletionCompletedScreen") { HelpDeletionCompletedScreen(navController)  }
                   composable("HelpSeeRequestScreen") { HelpSeeRequestScreen(navController)  }
                   composable("HelpHistoryListScreen") { HelpHistoryListScreen(navController)  }
                   composable("HelpGivePointsScreen") { HelpGivePointsScreen(navController)  }
                 
                   composable("HelpThankYouScreen") { HelpThankYouScreen(navController)  }
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
                    composable("SwapResultsFoundScreen") { SwapResultsFoundScreen(  ) }
                    composable("SwapSentRequestsScreen") { SwapSentRequestsScreen()  }

                     //----


        composable("SharingMainScreen") { SharingMainScreen(navController)  }






    }
}