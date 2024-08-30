package com.rokn.djur

import androidx.compose.ui.graphics.Color

sealed class DjurState {

    data object Win: DjurState()
    data object Loading: DjurState()
    data object Ongoing: DjurState() {
        val djurColumn1: MutableList<Djur> = mutableListOf()
        val djurColumn2: MutableList<Djur> = mutableListOf()
        val djurColumn3: MutableList<Djur> = mutableListOf()
        val djurColumn4: MutableList<Djur> = mutableListOf()
        var lastRemoved: Djur = Djur.Empty
        var noMovesPossible = false
        var noMoveMade = true
        fun restart() {
            djurColumn1.clear()
            djurColumn1.addAll(mutableListOf(
                Djur.Hund(Color.Green),
                Djur.Katt(Color.Red),
                Djur.Hare(Color.Yellow),
                Djur.Mose(Color.Blue)
            ).shuffled().toMutableList())
            djurColumn2.clear()
            djurColumn2.addAll(mutableListOf(
                Djur.Hund(Color.Red),
                Djur.Katt(Color.Yellow),
                Djur.Hare(Color.Blue),
                Djur.Mose(Color.Green)
            ).shuffled().toMutableList())
            djurColumn3.clear()
            djurColumn3.addAll(mutableListOf(
                Djur.Hund(Color.Yellow),
                Djur.Katt(Color.Blue),
                Djur.Hare(Color.Green),
                Djur.Mose(Color.Red)
            ).shuffled().toMutableList())
            djurColumn4.clear()
            djurColumn4.addAll(mutableListOf(
                Djur.Hund(Color.Blue),
                Djur.Katt(Color.Green),
                Djur.Hare(Color.Red),
                Djur.Mose(Color.Yellow)
            ).shuffled().toMutableList())
            lastRemoved = Djur.Empty
            noMovesPossible = false
            noMoveMade = true
        }
        fun shuffle() {
            djurColumn1.shuffle()
            djurColumn2.shuffle()
            djurColumn3.shuffle()
            djurColumn4.shuffle()
        }
    }
}