package com.rokn.djur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewModelScope
import com.rokn.djur.ui.theme.DjurTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DjurTheme {
                val viewModel = DjurViewModel()
                MainScreen(state = viewModel.uiState.collectAsState()) { column, id ->
                    viewModel.viewModelScope.launch {
                        viewModel.clicked(column, id)
                    }
                }
            }
        }
    }
}

