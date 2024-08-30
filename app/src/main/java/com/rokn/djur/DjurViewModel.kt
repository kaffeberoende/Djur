package com.rokn.djur

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DjurViewModel: ViewModel() {

    private val state = DjurState.Ongoing
    private val _uiState = MutableStateFlow<DjurState>(state)

    val uiState: StateFlow<DjurState> =  _uiState

    init {
        restart()
    }

    fun clicked(state: DjurState.Ongoing, column: Int, id: String) {
        viewModelScope.launch {
            _uiState.emit(DjurState.Loading)
            delay(100)
            state.noMoveMade = false
            when (column) {
                1 -> handleRemoval(state.djurColumn1, id)
                2 -> handleRemoval(state.djurColumn2, id)
                3 -> handleRemoval(state.djurColumn3, id)
                4 -> handleRemoval(state.djurColumn4, id)
            }

            if (checkForWin()) {
                _uiState.emit(DjurState.Win)
            } else {
                state.noMovesPossible = checkForLoss()
                _uiState.emit(state)
            }
        }
    }

    fun shuffle() {
        state.shuffle()
        viewModelScope.launch {
            _uiState.emit(DjurState.Loading)
            delay(100)
            _uiState.emit(state)
        }
    }

    fun restart() {
        state.restart()
        viewModelScope.launch {
            _uiState.emit(DjurState.Loading)
            delay(100)
            _uiState.emit(state)
        }
    }

    private fun handleRemoval(column: MutableList<Djur>, id: String) {
        val row = column.indexOfFirst { it.id == id }
        if (row == column.size - 1 && allowedToRemove(column[row])) {
            state.lastRemoved = column[row]
            for (i in column.size - 1 downTo 1)
                column[i] = column[i - 1]
            column[0] = Djur.Empty
        }
    }

    private fun allowedToRemove(djur: Djur): Boolean {
        return when {
            state.lastRemoved is Djur.Empty -> true
            state.noMovesPossible -> false
            else -> {
                djur::class.java == state.lastRemoved::class.java ||
                        djur.color == state.lastRemoved.color
            }
        }
    }

    private fun checkForWin(): Boolean {
        return state.djurColumn1.all { it is Djur.Empty } &&
                state.djurColumn2.all { it is Djur.Empty } &&
                state.djurColumn3.all { it is Djur.Empty } &&
                state.djurColumn4.all { it is Djur.Empty }
    }

    private fun checkForLoss(): Boolean {
        val bottomRow = listOf(
            state.djurColumn1.last(),
            state.djurColumn2.last(),
            state.djurColumn3.last(),
            state.djurColumn4.last()
        )
        return when (state.lastRemoved) {
            is Djur.Empty -> false
            else -> {
                bottomRow.none { djur ->
                    djur::class.java == state.lastRemoved::class.java ||
                            djur.color == state.lastRemoved.color
                }
            }
        }
    }
}