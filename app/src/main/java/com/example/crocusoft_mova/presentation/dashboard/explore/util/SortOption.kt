package com.example.crocusoft_mova.presentation.dashboard.explore.util

enum class SortOption(val displayName: String, val apiValue: String) {
    POPULARITY("Popularity", "popularity.desc"),
    LATEST_RELEASE("Latest Release", "primary_release_date.desc")
}
