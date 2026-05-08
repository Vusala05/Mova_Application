package com.example.crocusoft_mova.presentation.auth.splash
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.crocusoft_mova.common.components.AppButton
import com.example.crocusoft_mova.core.Strings
import com.example.crocusoft_mova.presentation.auth.splash.components.OnBoardingPage
import com.example.crocusoft_mova.presentation.auth.splash.components.PagerIndicator


@Composable
fun OnboardingContent(
    onNavSignChoice: ()->Unit
){

    val pagerState = rememberPagerState(pageCount = { 3 })
    val pages = listOf(
        Pair(stringResource(Strings.splash_first_title), stringResource(Strings.splash_first_description)),
        Pair(stringResource(Strings.splash_first_title), stringResource(Strings.splash_first_description)),
        Pair(stringResource(Strings.splash_first_title), stringResource(Strings.splash_first_description)),
    )
    Box(modifier = Modifier.fillMaxSize()) {

        HorizontalPager(state = pagerState) { page ->

            OnBoardingPage(
                title = pages[page].first,
                description = pages[page].second
            )


        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PagerIndicator(
                pageCount = 3,
                currentPage = pagerState.currentPage
            )

            Spacer(modifier = Modifier.height(16.dp))


            AppButton(action = {onNavSignChoice()},
                text = "Get Started")
        }
    }
}