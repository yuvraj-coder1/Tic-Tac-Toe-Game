package com.example.tictactoe.ui

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
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

class GameViewModel() :ViewModel() {
    //    var currentplayerturn by mutableStateOf("")
//        private set
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    @SuppressLint("SuspiciousIndentation")
    fun HandleUserMove(boxNumber: Int) {
        val currentStatusOfBox: String = uiState.value.iconToDisplayOnBoard[boxNumber - 1]

        if (currentStatusOfBox == "empty") {
            // Create a new list with the updated value
            var updatedIsPlayer1Turn: Boolean
            val updatedIconToDisplayOnBoard = uiState.value.iconToDisplayOnBoard.toMutableList()
            if (_uiState.value.isPlayer1Turn) {
                updatedIconToDisplayOnBoard[boxNumber - 1] = "Cross"
                updatedIsPlayer1Turn = false
            } else {
                updatedIconToDisplayOnBoard[boxNumber - 1] = "Zero"
                updatedIsPlayer1Turn = true
            }
            _uiState.update { currentState ->
                currentState.copy(
                    iconToDisplayOnBoard = updatedIconToDisplayOnBoard,
                    isPlayer1Turn = updatedIsPlayer1Turn
                )
            }
            CheckIfGameOver()
        }
    }

    fun CheckIfGameOver() {
        var isGameOver: Boolean = false
        val iconList = uiState.value.iconToDisplayOnBoard.toMutableList()
        if (iconList[1] != "empty" && (iconList[0] == iconList[1]) && (iconList[1] == iconList[2])) {
            isGameOver = true
        } else if (iconList[3] != "empty" && (iconList[0] == iconList[3]) && (iconList[3] == iconList[6])) {
            isGameOver = true
        } else if (iconList[5] != "empty" && (iconList[2] == iconList[5]) && (iconList[5] == iconList[8])) {
            isGameOver = true
        } else if (iconList[7] != "empty" && (iconList[6] == iconList[7]) && (iconList[7] == iconList[8])) {
            isGameOver = true
        } else if (iconList[4] != "empty") {
            if ((iconList[1] == iconList[4]) && (iconList[4] == iconList[7])) {
                isGameOver = true
            } else if ((iconList[3] == iconList[4]) && (iconList[4] == iconList[5])) {
                isGameOver = true
            } else if ((iconList[0] == iconList[4]) && (iconList[4] == iconList[8])) {
                isGameOver = true
            } else if ((iconList[2] == iconList[4]) && (iconList[4] == iconList[6])) {
                isGameOver = true
            }
        }
        var checkIfAllBoxFilled:Boolean=true
        iconList.forEach(){
            if(it=="empty")
                checkIfAllBoxFilled=false
        }
        if(checkIfAllBoxFilled)
            isGameOver=true
        _uiState.update { currentState ->
            currentState.copy(
                isGameOver = isGameOver
            )
        }
    }
}
