package com.example.crocusoft_mova.presentation.dashboard.movie_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.crocusoft_mova.common.components.AppTopBar
import com.example.crocusoft_mova.common.components.MovieGrid
import com.example.crocusoft_mova.core.BaseTheme
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.core.Drawables
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun MovieListContent(
    effect : SharedFlow<MovieListContract.Effect>,
    state : MovieListContract.State,
    onBackClick : () -> Unit,
    navigateToDetail : (Int, String) -> Unit
){
    val context = LocalContext.current
    LaunchedEffect(Unit) {
     effect.collect {
         when(it){
             is MovieListContract.Effect.ShowError ->{
                 Toast.makeText(context,it.message, Toast.LENGTH_LONG).show()
             }
         }
     }
    }
    Scaffold(modifier = Modifier.fillMaxSize().padding(BaseTheme.dimens.dp6), containerColor = colorResource(Colors.primary),
        topBar = {
            AppTopBar(
                modifier = Modifier.padding(bottom = BaseTheme.dimens.dp6),
                prefixIcon = Drawables.back,
                prefixAction = onBackClick,
                title = state.title)
        }) { paddingValues ->
        if(state.isLoading){
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
                ){
                CircularProgressIndicator(color = colorResource(Colors.secondary))
            }
        }else{
            MovieGrid(
                movies = state.movieList,
                onMovieClick = navigateToDetail,
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            )
        }

    }


}