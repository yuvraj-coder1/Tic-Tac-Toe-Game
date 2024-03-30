package com.example.tictactoe.ui

data class GameUiState(
   val player1Score:Int=0,
   val player2Score:Int=0,
   val isGameOver:Boolean=false,
   val iconToDisplayOnBoard:MutableList<String> = MutableList(9) { "empty" },
   val isPlayer1Turn:Boolean = true,
   val isDraw:Boolean = false,
   var isDarkTheme:Boolean = false
)
