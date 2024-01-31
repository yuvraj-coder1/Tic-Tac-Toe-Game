package com.example.tictactoe.ui

data class GameUiState(
   val player1Score:Int=0,
   val player2Score:Int=0,
   val isGameOver:Boolean=false,
   var iconToDisplayOnBoard:MutableList<String> = MutableList(9) { "empty" },
   var isPlayer1Turn:Boolean = true
)
