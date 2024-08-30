package com.rokn.djur

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rokn.djur.ui.theme.DjurTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun MainScreen(
    state: State<DjurState>,
    clicked: (DjurState.Ongoing, Int, String) -> Unit,
    shuffleClicked: () -> Unit,
    restartClicked: () -> Unit,
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
                    Column {
                        Row(
                            verticalAlignment = Alignment.Bottom
                        ) {
                            DjurColumn(items = uiState.djurColumn1) {
                                clicked(uiState,1, it)
                            }
                            DjurColumn(items = uiState.djurColumn2) {
                                clicked(uiState, 2, it)
                            }
                            DjurColumn(items = uiState.djurColumn3) {
                                clicked(uiState, 3, it)
                            }
                            DjurColumn(items = uiState.djurColumn4) {
                                clicked(uiState, 4, it)
                            }
                        }
                        Spacer(modifier = Modifier.height(48.dp))
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            DjurItem(typ = uiState.lastRemoved) {
                            }
                        }
                        if (uiState.noMovesPossible) {
                            Spacer(modifier = Modifier.height(48.dp))
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "No moves possible. You lose.")
                                Button(onClick = restartClicked) {
                                    Text(text = "Restart")
                                }

                            }
                        }
                        if (uiState.noMoveMade) {
                        Row {
                            Button(onClick = shuffleClicked) {
                                Text(text = "Shuffle")
                            }
                        }
                        }

                    }

                }
                is DjurState.Loading -> {
                    Log.d("ROKN", "Loading")
                }
                is DjurState.Win ->  WinView()
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
            clicked = { _, _, _ -> },
            shuffleClicked = {},
            restartClicked = {}
        )
    }
}
