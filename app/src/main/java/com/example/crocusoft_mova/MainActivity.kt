package com.example.crocusoft_mova

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.crocusoft_mova.core.Colors
import com.example.crocusoft_mova.presentation.App
import com.example.crocusoft_mova.ui.theme.Crocusoft_movaTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkModeActive by viewModel.isDarkModeActive.collectAsState(initial = false)
            LaunchedEffect(isDarkModeActive) {
                Log.e("DARK MODE STATE:" ,"$isDarkModeActive")
            }
            Crocusoft_movaTheme {
                Scaffold(
                    containerColor = colorResource(Colors.primary),
                    modifier = Modifier
                        .fillMaxSize()


                ) { innerPadding ->
                    App(innerPaddingValues = innerPadding, firebaseAuth, isDarkMode = isDarkModeActive,
                        onDarkModeChange = { isDark ->
                            viewModel.updateIsDarkModeActive(isDark)
                        })
                }
            }
        }
    }
}
