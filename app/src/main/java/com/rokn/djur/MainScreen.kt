package com.rokn.djur

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rokn.djur.ui.theme.DjurTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun MainScreen(
    state: State<DjurState>,
    clicked: (Int, String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Crossfade(
            targetState = state.value,
            animationSpec = tween(1000),
            label = "ani"
        ) { uiState ->
            when (uiState) {
                is DjurState.Ongoing -> {
                    Log.d("ROKN", "Ongoing")
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        DjurColumn(items = uiState.djurColumn1) {
                            clicked(1, it)
                        }
                        DjurColumn(items = uiState.djurColumn2) {
                            clicked(2, it)
                        }
                        DjurColumn(items = uiState.djurColumn3) {
                            clicked(3, it)
                        }
                        DjurColumn(items = uiState.djurColumn4) {
                            clicked(4, it)
                        }
                    }
                }
                is DjurState.Loading -> {
                    Log.d("ROKN", "Loading")
                }

                else -> {
                    WinView()
                }
            }
        }
    }
}

@Composable
@Preview
fun ScreenPreview() {
    DjurTheme {
        MainScreen(
            state = MutableStateFlow<DjurState>(DjurState.Ongoing).asStateFlow().collectAsState(),
            clicked = { _, _ -> }
        )
    }
}
