package com.example.crocusoft_mova.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.BottomSheetNavigations
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.ui.navigation.AppRoutes
import com.example.crocusoft_mova.ui.navigation.MainRoutes
import com.google.firebase.auth.FirebaseAuth


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(innerPaddingValues: PaddingValues, firebaseAuth: FirebaseAuth, isDarkMode: Boolean,
        onDarkModeChange: (Boolean) -> Unit) {

    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val hiddenRoutes = listOf(
        AppRoutes.AuthRoute.SignChoice::class.qualifiedName,
        AppRoutes.AuthRoute.NewPin::class.qualifiedName,
        AppRoutes.AuthRoute.SignUp::class.qualifiedName,
        AppRoutes.AuthRoute.SignIn::class.qualifiedName,
        AppRoutes.AuthRoute.ChooseInterest::class.qualifiedName,
        AppRoutes.AuthRoute.FillProfile::class.qualifiedName,
        AppRoutes.DashboardRoute.Pin::class.qualifiedName,
        AppRoutes.AuthRoute.Splash::class.qualifiedName,
        AppRoutes.DashboardRoute.MovieList::class.qualifiedName,
        AppRoutes.DashboardRoute.MovieDetail::class.qualifiedName?.substringBeforeLast("/") ?: ""
    )

    Log.e("current class :","${AppRoutes.DashboardRoute.MovieDetail::class.qualifiedName}")
    Log.e("current route","$currentRoute")

    Scaffold(
        bottomBar = {

            val isMovieDetail = currentRoute?.startsWith("MovieDetail") == true

            if (currentRoute !in hiddenRoutes ) {

                Surface(
                    color = Color.Transparent,
                    tonalElevation = 0.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(BaseTheme.dimens.bottom_bar_height)

                ) {

                    NavigationBar(
                        containerColor = colorResource(Colors.primary),
                    ) {
                        BottomSheetNavigations.navigations.forEach { route ->
                            val isSelected =
                                currentRoute == route.route::class.qualifiedName

                            NavigationBarItem(
                                selected = isSelected,
                                onClick = {
                                    if (!isSelected) {
                                        navController.navigate(route.route)
                                    }
                                },
                                icon = {
                                    androidx.compose.material3.Icon(
                                        contentDescription = null,
                                        painter = painterResource(route.icon),
                                        tint = if (isSelected)
                                            colorResource(Colors.secondary)
                                        else
                                            colorResource(Colors.gray)
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(route.id),
                                        style = BaseTheme.textStyle.t10Bold.copy(
                                            color = if (isSelected) colorResource(
                                                Colors.secondary
                                            ) else colorResource(Colors.gray)
                                        )
                                    )
                                },
                                alwaysShowLabel = true,
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Transparent,
                                )
                            )
                        }
                    }
                }
            }


        },
        containerColor = colorResource(Colors.ic_launcher_background),
        modifier = Modifier.padding(
            if (currentRoute == AppRoutes.DashboardRoute.Home::class.qualifiedName || (currentRoute?.startsWith(AppRoutes.DashboardRoute.MovieDetail::class.qualifiedName?.substringBeforeLast(".") ?: "") == true)) PaddingValues(
                0.dp
            ) else innerPaddingValues
        )
    ) {
        MainRoutes(
            innerPadding = innerPaddingValues,
            navController = navController,
            firebaseAuth = firebaseAuth,
            isDarkMode = isDarkMode,
            onDarkModeChange = onDarkModeChange
        )
    }

}
