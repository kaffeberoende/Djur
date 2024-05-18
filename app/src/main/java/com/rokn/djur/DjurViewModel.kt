package com.rokn.djur

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

    fun clicked(column: Int, id: String) {
        when(uiState.value) {
            is DjurState.Ongoing -> {
                viewModelScope.launch {
                    _uiState.emit(DjurState.Loading)
                    delay(100)
                    when (column) {
                        1 -> handleRemoval(state.djurColumn1, id)
                        2 -> handleRemoval(state.djurColumn2, id)
                        3 -> handleRemoval(state.djurColumn3, id)
                        4 -> handleRemoval(state.djurColumn4, id)
                    }
                    _uiState.emit(state)
                }
            }
            else -> Unit
        }
    }

    private fun handleRemoval(column: MutableList<Djur>, id: String) {
        val row = column.indexOfFirst { it.id == id }
        if (row == column.size - 1) {
            for (i in column.size - 1 downTo 1)
                column[i] = column[i - 1]
            column[0] = Djur.Empty
        }
    }
}