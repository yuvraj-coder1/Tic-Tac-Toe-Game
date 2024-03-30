package com.example.tictactoe.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel() :ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    @SuppressLint("SuspiciousIndentation")
    fun HandleUserMove(boxNumber: Int) {
        val currentStatusOfBox: String = uiState.value.iconToDisplayOnBoard[boxNumber - 1]

        if (currentStatusOfBox == "empty") {
            // Create a new list with the updated value
            val updatedIsPlayer1Turn: Boolean
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
        var isGameOver = false
        var isDraw = false
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

        var checkIfAllBoxFilled =true
        iconList.forEach(){
            if(it=="empty")
                checkIfAllBoxFilled=false
        }

        if(checkIfAllBoxFilled&&!isGameOver)
        {
            isGameOver=true
            isDraw=true
        }

        _uiState.update { currentState ->
            currentState.copy(
                isGameOver = isGameOver,
                isDraw = isDraw
            )
        }
        IncreaseScore()
    }
    fun IncreaseScore() {
        if(!uiState.value.isDraw&&uiState.value.isGameOver)
        {
            when(uiState.value.isPlayer1Turn)
            {
                true->{_uiState.update { currentState ->
                    currentState.copy(
                        player2Score=  uiState.value.player2Score + 1
                    )
                }}
                else ->{
                    _uiState.update { currentState ->
                        currentState.copy(
                            player1Score=  uiState.value.player1Score + 1
                        )
                    }
                }
            }
        }
    }
    fun NewGame() {
        _uiState.update { currentState ->
            currentState.copy(
                isDraw = false,
                isPlayer1Turn = true,
                isGameOver = false,
                iconToDisplayOnBoard = MutableList(9) { "empty" }
            )
        }
    }
    fun ResetGame() {
        _uiState.update { currentState ->
            currentState.copy(
                isDraw = false,
                isPlayer1Turn = true,
                isGameOver = false,
                iconToDisplayOnBoard = MutableList(9) { "empty" },
                player2Score = 0,
                player1Score = 0
            )
        }
    }
    fun ChangeTheme() {
        if(uiState.value.isDarkTheme) {
            _uiState.update { currentState ->
                currentState.copy(
                    isDarkTheme = false
                )
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    isDarkTheme = true
                )
            }
        }
    }
}
