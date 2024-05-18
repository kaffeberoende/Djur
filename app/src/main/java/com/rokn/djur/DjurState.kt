package com.rokn.djur

import androidx.compose.ui.graphics.Color

sealed class DjurState {

    data object Win: DjurState()
    data object Loading: DjurState()
    data object Ongoing: DjurState() {
        val djurColumn1: MutableList<Djur> = mutableListOf(
            Djur.Hund(Color.Green),
            Djur.Katt(Color.Red),
            Djur.Hare(Color.Yellow),
            Djur.Mose(Color.Blue)
        ).shuffled().toMutableList()
        val djurColumn2: MutableList<Djur> = mutableListOf(
            Djur.Hund(Color.Red),
            Djur.Katt(Color.Yellow),
            Djur.Hare(Color.Blue),
            Djur.Mose(Color.Green)
        ).shuffled().toMutableList()
        val djurColumn3: MutableList<Djur> = mutableListOf(
            Djur.Hund(Color.Yellow),
            Djur.Katt(Color.Blue),
            Djur.Hare(Color.Green),
            Djur.Mose(Color.Red)
        ).shuffled().toMutableList()
        val djurColumn4: MutableList<Djur> = mutableListOf(
            Djur.Hund(Color.Blue),
            Djur.Katt(Color.Green),
            Djur.Hare(Color.Red),
            Djur.Mose(Color.Yellow)
        ).shuffled().toMutableList()
        var lastRemoved: Djur = Djur.Empty
        var noMovesPossible = false
    }
}