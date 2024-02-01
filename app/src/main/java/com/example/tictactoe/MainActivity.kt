package com.example.tictactoe

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.ui.GameViewModel
import com.example.tictactoe.ui.theme.TicTacToeTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TickTacToeScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun  TickTacToeScreen() {
   Scaffold(
topBar = { TickTacToeTopBar() }
   ) {
       Box(modifier = Modifier.padding(top = 100.dp)) {
           TickTacToeGameLayout()
       }
   }
}

@Composable
fun TickTacToeGameLayout(
    modifier: Modifier=Modifier,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    Column {
        CurrentScore(modifier=Modifier, player1Score = gameUiState.player1Score, player2Score = gameUiState.player2Score)
        Spacer(modifier = modifier.size(20.dp))
        if(!gameUiState.isGameOver)
        {
            CurrentPlayerTurn(
                modifier=Modifier,
                isPlayer1Turn =  gameUiState.isPlayer1Turn
            )
        }
        else
        {
              GameWinner(modifier= Modifier, player1Won = !gameUiState.isPlayer1Turn,isDraw = gameUiState.isDraw )
        }
        Spacer(modifier = modifier.size(20.dp))
        Column(
            modifier=Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Row {
                TickTacToeButton(
                    modifier = Modifier,
                    boxNumber = 1,
                    onBoxClicked = { gameViewModel.HandleUserMove(1) },
                    currentIconToDisplay = gameUiState.iconToDisplayOnBoard[0],
                    isGameOver = gameUiState.isGameOver
                )
                TickTacToeButton(modifier = Modifier, boxNumber = 2,onBoxClicked = {gameViewModel.HandleUserMove(2)},currentIconToDisplay = gameUiState.iconToDisplayOnBoard[1],isGameOver = gameUiState.isGameOver)
                TickTacToeButton(modifier = Modifier, boxNumber = 3,onBoxClicked = {gameViewModel.HandleUserMove(3)},currentIconToDisplay = gameUiState.iconToDisplayOnBoard[2],isGameOver = gameUiState.isGameOver)
            }
            Row {
                TickTacToeButton(modifier = Modifier, boxNumber = 4,onBoxClicked = {gameViewModel.HandleUserMove(4)},currentIconToDisplay = gameUiState.iconToDisplayOnBoard[3],isGameOver = gameUiState.isGameOver)
                TickTacToeButton(modifier = Modifier, boxNumber = 5,onBoxClicked = {gameViewModel.HandleUserMove(5)},currentIconToDisplay = gameUiState.iconToDisplayOnBoard[4],isGameOver = gameUiState.isGameOver)
                TickTacToeButton(modifier = Modifier, boxNumber = 6,onBoxClicked = {gameViewModel.HandleUserMove(6)},currentIconToDisplay = gameUiState.iconToDisplayOnBoard[5],isGameOver = gameUiState.isGameOver)
            }
            Row {
                TickTacToeButton(modifier = Modifier, boxNumber = 7,onBoxClicked = {gameViewModel.HandleUserMove(7)},currentIconToDisplay = gameUiState.iconToDisplayOnBoard[6],isGameOver = gameUiState.isGameOver)
                TickTacToeButton(modifier = Modifier, boxNumber = 8,onBoxClicked = {gameViewModel.HandleUserMove(8)},currentIconToDisplay = gameUiState.iconToDisplayOnBoard[7],isGameOver = gameUiState.isGameOver)
                TickTacToeButton(modifier = Modifier, boxNumber = 9,onBoxClicked = {gameViewModel.HandleUserMove(9)},currentIconToDisplay = gameUiState.iconToDisplayOnBoard[8],isGameOver = gameUiState.isGameOver)
            }
        }
        Spacer(modifier = modifier.size(20.dp))
        Column(
            modifier=Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { gameViewModel.NewGame() },modifier=Modifier.padding(10.dp), colors = ButtonDefaults.buttonColors(
                contentColor = Color.Red)) {
                Text(stringResource(R.string.new_game))
            }
            Button(onClick = { gameViewModel.ResetGame() },modifier=Modifier.padding(10.dp), colors = ButtonDefaults.buttonColors(
                contentColor = Color.Red)) {
                Text(stringResource(R.string.reset_game))
            }
        }

    }
}

@Composable
fun GameWinner(modifier: Modifier,
    player1Won:Boolean,
               isDraw:Boolean
) {
    if(isDraw)
    {
        Text(
            text = stringResource(R.string.draw),
            style = MaterialTheme.typography.headlineLarge,
            modifier=modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
   else if(player1Won)
   {
       Text(
           text = stringResource(R.string.player1_won),
           style = MaterialTheme.typography.headlineLarge,
           modifier=modifier.fillMaxWidth(),
           textAlign = TextAlign.Center,
           color = Color.Red
       )
   }
    else
    {
        Text(
            text = stringResource(R.string.player2_won),
            style = MaterialTheme.typography.headlineLarge,
            modifier=modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Blue
        )
    }
}


@Composable
fun CurrentScore(
    modifier: Modifier=Modifier,
    player1Score:Int,
    player2Score:Int,
)
{
   Row (
       horizontalArrangement = Arrangement.SpaceBetween,
       modifier= modifier
           .fillMaxWidth()
           .padding(horizontal = 20.dp)
   ){
       Card(
           modifier= Modifier
               .size(50.dp),
//           colors = CardDefaults.cardColors(
//               containerColor = Color.Red
//           )
       ) {
           Column(
               modifier=Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               Text(
                   text = player1Score.toString(),
                   textAlign = TextAlign.Center
               )
           }
       }
       Card(
           modifier=Modifier.size(50.dp),
       ) {
           Column(
               modifier=Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               Text(

                   text = player2Score.toString(),
                   textAlign = TextAlign.Center
               )
           }
       }
   }
}

@Composable
fun CurrentPlayerTurn(
    modifier: Modifier=Modifier,
    isPlayer1Turn:Boolean
)
{
    if(isPlayer1Turn)
    {
        Text(
            text = stringResource(R.string.player_1_turn),
            style = MaterialTheme.typography.headlineLarge,
            modifier=modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Red
        )
    }
    else
    {
        Text(
            text = stringResource(R.string.player_2_turn),
            style = MaterialTheme.typography.headlineLarge,
            modifier=modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Blue
        )
    }

}


@Composable
fun TickTacToeButton(
    modifier: Modifier=Modifier,
    boxNumber:Int,
    onBoxClicked:(Int)->Unit,
    currentIconToDisplay:String,
    isGameOver:Boolean
) {
    IconButton(modifier = modifier
        .padding(5.dp)
        .size(65.dp)
        .border(BorderStroke(2.dp, Color.Black))
        , onClick = {
            if(!isGameOver)
            onBoxClicked(boxNumber)
        }
    )
        {
            if(currentIconToDisplay=="Cross")
            {
                Icon(

                    imageVector =Icons.Filled.Clear,
                    contentDescription = "Cross",
                    tint = Color.Red,
                    modifier = Modifier.size(48.dp)
                )
            }
            else if(currentIconToDisplay=="Zero") {
                Icon(

                    imageVector =Icons.Rounded.Search,
                    contentDescription = "Zero",
                    tint = Color.Blue,
                    modifier = Modifier.size(48.dp)
                )
            }
            else {

            }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TickTacToeTopBar(modifier: Modifier = Modifier) {
   CenterAlignedTopAppBar(
       
       title = {
           Row(
               verticalAlignment = Alignment.CenterVertically,

           ) {
               Text(
                   text = stringResource(R.string.tic_tac_toe),
                   style = MaterialTheme.typography.displayMedium
               )
           }
       }
   )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun  TickTacToeScreenPreview() {
    TicTacToeTheme {
        TickTacToeScreen()
    }
}