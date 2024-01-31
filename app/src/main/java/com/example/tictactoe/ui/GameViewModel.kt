package com.example.tictactoe.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
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

    @SuppressLint("SuspiciousIndentation")
    fun HandleUserMove(boxNumber:Int) {

        Log.d("Value Changed", "Button Clicked: Box Number = $boxNumber")
        val currentUiState = uiState.value.copy()

        // Access the iconToDisplayOnBoard list from the copied state
        val currentStatusOfBox: String = currentUiState.iconToDisplayOnBoard[boxNumber - 1]
        Log.d("Value Changed", "Button Clicked: Box Number = ${currentUiState.iconToDisplayOnBoard[boxNumber - 1]}")
        if (currentStatusOfBox == "empty") {
            // Create a new list with the updated value
            val updatedIconToDisplayOnBoard = currentUiState.iconToDisplayOnBoard.toMutableList()
            updatedIconToDisplayOnBoard[boxNumber - 1] = "Cross"

            // Assign the new list to iconToDisplayOnBoard
            currentUiState.iconToDisplayOnBoard = updatedIconToDisplayOnBoard

            // Update the _uiState with the modified GameUiState
            _uiState.value = currentUiState
        }
    }
}
