package com.example.crocusoft_mova.core


import com.example.crocusoft_mova.ui.navigation.AppRoutes

data class BottomSheetNavigations(
    val route: AppRoutes.DashboardRoute,
    val id: Int,
    val icon: Int,
) {
    companion object {
        val navigations = listOf(
            BottomSheetNavigations(
                AppRoutes.DashboardRoute.Home,
                Strings.home,
                Drawables.home
            ),
            BottomSheetNavigations(
                route = AppRoutes.DashboardRoute.Explore,
                id = Strings.explore,
                icon = Drawables.explore
            ),
            BottomSheetNavigations(
                route = AppRoutes.DashboardRoute.MyList,
                id = Strings.my_list,
                icon = Drawables.bookmark
            ),
            BottomSheetNavigations(
                route = AppRoutes.DashboardRoute.Download,
                id = Strings.download,
                icon = Drawables.download
            ),
            BottomSheetNavigations(
                route = AppRoutes.DashboardRoute.Profile,
                id = Strings.profile,
                icon = Drawables.profile
            ),

            )

    }
}
