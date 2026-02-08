package com.paylastikca.presentation.view.profilescreens.application_preferences_screens

data class LanguageOption(
    val code: String,
    val label: String
)

val AppLanguages = listOf(
    LanguageOption("tr", "Türkçe"),
    LanguageOption("en", "English"),
    LanguageOption("fr", "French"),
    LanguageOption("de", "German")
)
