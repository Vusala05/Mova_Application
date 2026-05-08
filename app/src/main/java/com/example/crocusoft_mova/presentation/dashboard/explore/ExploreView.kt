package com.example.crocusoft_mova.presentation.dashboard.explore

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crocusoft_mova.ui.navigation.AppRoutes


@Composable
fun ExploreView(navController: NavController, viewModel: ExploreViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ExploreContent(
        state = state,
        postIntent = viewModel::onIntent,
        onNavigateDetail = { id,type->
            if(type=="movie"){
            navController.navigate(AppRoutes.DashboardRoute.MovieDetail(movieId = id))
            } else{
                Toast.makeText(context,"Tv Detail Movcud deyil", Toast.LENGTH_SHORT).show()
            }
        } ,
        effect = viewModel.effect
    )
}