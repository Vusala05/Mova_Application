package com.example.crocusoft_mova.ui.navigation

import com.example.crocusoft_mova.core.MovieCategoryType
import kotlinx.serialization.Serializable

sealed interface AppRoutes {


    sealed interface AuthRoute {

        @Serializable
        data object Splash: AuthRoute

        @Serializable
        data object NewPin : AuthRoute

        @Serializable
        data object SignUp : AuthRoute

        @Serializable
        data object SignIn : AuthRoute


        @Serializable
        data object SignChoice : AuthRoute


        @Serializable
        data object ChooseInterest : AuthRoute


        @Serializable
        data class FillProfile(val isEdit : Boolean ) : AuthRoute
    }

    sealed interface DashboardRoute {


        @Serializable
        data class MovieDetail(val movieId: Int)

        @Serializable
        data object Explore:DashboardRoute

        @Serializable
        data object MyList:DashboardRoute

        @Serializable
        data object Profile:DashboardRoute

        @Serializable
        data object Download: DashboardRoute
        @Serializable
        data object Pin : DashboardRoute

        @Serializable
        data object Home : DashboardRoute
        @Serializable
        data object Language : DashboardRoute
        @Serializable
        data class MovieList(val categoryType: MovieCategoryType) : DashboardRoute
    }


}