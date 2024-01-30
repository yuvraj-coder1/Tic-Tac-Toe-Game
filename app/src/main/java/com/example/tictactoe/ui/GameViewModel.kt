package com.example.tictactoe.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel:ViewModel() {
    var currentplayerturn by mutableStateOf("")
        private set
    private val _uiState=MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    val iconToDisplayOnBoard:MutableList<String> = MutableList(9) { "empty" }
    fun HandleUserMove(boxNumber:Int) {
       val currentStatusOfBox:String=iconToDisplayOnBoard[boxNumber-1]
        if(currentStatusOfBox=="empty") {
          iconToDisplayOnBoard[boxNumber-1]="Cross"
        }
    }
}