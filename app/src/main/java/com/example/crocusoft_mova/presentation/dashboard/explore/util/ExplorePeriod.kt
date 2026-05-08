package com.example.crocusoft_mova.presentation.dashboard.explore.util

import android.icu.util.Calendar
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreContract.State.Companion.DEFAULT_TIME

object ExplorePeriod {
    private const val YEAR_LIMIT = 20

    fun getPeriodOption() : List<String>{
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = (currentYear downTo currentYear - YEAR_LIMIT).map { it.toString() }
        return  listOf(DEFAULT_TIME) + years
    }
}