package com.example.tictactoe

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.ui.GameUiState
import com.example.tictactoe.ui.GameViewModel
import com.example.tictactoe.ui.theme.TicTacToeTheme
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gameViewModel: GameViewModel = viewModel()
            val gameUiState by gameViewModel.uiState.collectAsState()
            TicTacToeTheme(
                darkTheme = gameUiState.isDarkTheme
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TickTacToeScreen(
                        gameViewModel = gameViewModel,
                        gameUiState = gameUiState
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TickTacToeScreen(
    gameViewModel: GameViewModel,
    gameUiState: GameUiState
) {
    Scaffold(
        topBar = {
            TickTacToeTopBar(
                gameUiState = gameUiState,
                gameViewModel = gameViewModel
            )
        }
    ) {
        Box(modifier = Modifier.padding(top = 100.dp)) {
            TickTacToeGameLayout(
                gameViewModel = gameViewModel,
                gameUiState = gameUiState
            )
        }
    }
}

@Composable
fun TickTacToeGameLayout(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(),
    gameUiState: GameUiState
) {
    Column {
        CurrentScore(
            modifier = Modifier,
            player1Score = gameUiState.player1Score,
            player2Score = gameUiState.player2Score
        )
        Spacer(modifier = modifier.size(20.dp))
        if (!gameUiState.isGameOver) {
            CurrentPlayerTurn(
                modifier = Modifier,
                isPlayer1Turn = gameUiState.isPlayer1Turn
            )
        } else {
            GameWinner(
                modifier = Modifier,
                player1Won = !gameUiState.isPlayer1Turn,
                isDraw = gameUiState.isDraw,
                gameViewModel = gameViewModel
            )
        }
        Spacer(modifier = modifier.size(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
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
                TickTacToeButton(
                    modifier = Modifier,
                    boxNumber = 2,
                    onBoxClicked = { gameViewModel.HandleUserMove(2) },
                    currentIconToDisplay = gameUiState.iconToDisplayOnBoard[1],
                    isGameOver = gameUiState.isGameOver
                )
                TickTacToeButton(
                    modifier = Modifier,
                    boxNumber = 3,
                    onBoxClicked = { gameViewModel.HandleUserMove(3) },
                    currentIconToDisplay = gameUiState.iconToDisplayOnBoard[2],
                    isGameOver = gameUiState.isGameOver
                )
            }
            Row {
                TickTacToeButton(
                    modifier = Modifier,
                    boxNumber = 4,
                    onBoxClicked = { gameViewModel.HandleUserMove(4) },
                    currentIconToDisplay = gameUiState.iconToDisplayOnBoard[3],
                    isGameOver = gameUiState.isGameOver
                )
                TickTacToeButton(
                    modifier = Modifier,
                    boxNumber = 5,
                    onBoxClicked = { gameViewModel.HandleUserMove(5) },
                    currentIconToDisplay = gameUiState.iconToDisplayOnBoard[4],
                    isGameOver = gameUiState.isGameOver
                )
                TickTacToeButton(
                    modifier = Modifier,
                    boxNumber = 6,
                    onBoxClicked = { gameViewModel.HandleUserMove(6) },
                    currentIconToDisplay = gameUiState.iconToDisplayOnBoard[5],
                    isGameOver = gameUiState.isGameOver
                )
            }
            Row {
                TickTacToeButton(
                    modifier = Modifier,
                    boxNumber = 7,
                    onBoxClicked = { gameViewModel.HandleUserMove(7) },
                    currentIconToDisplay = gameUiState.iconToDisplayOnBoard[6],
                    isGameOver = gameUiState.isGameOver
                )
                TickTacToeButton(
                    modifier = Modifier,
                    boxNumber = 8,
                    onBoxClicked = { gameViewModel.HandleUserMove(8) },
                    currentIconToDisplay = gameUiState.iconToDisplayOnBoard[7],
                    isGameOver = gameUiState.isGameOver
                )
                TickTacToeButton(
                    modifier = Modifier,
                    boxNumber = 9,
                    onBoxClicked = { gameViewModel.HandleUserMove(9) },
                    currentIconToDisplay = gameUiState.iconToDisplayOnBoard[8],
                    isGameOver = gameUiState.isGameOver
                )
            }
        }
        Spacer(modifier = modifier.size(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Button(
//                onClick = { gameViewModel.NewGame() },
//                modifier = Modifier.padding(10.dp),
//                colors = ButtonDefaults.buttonColors(
//                    contentColor = Color.Red
//                )
//            ) {
//                Text(stringResource(R.string.new_game))
//            }
            Button(
                onClick = { gameViewModel.ResetGame() },
                modifier = Modifier.padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Red
                )
            ) {
                Text(stringResource(R.string.reset_game))
            }
        }

    }
}


@Composable
fun GameWinner(
    modifier: Modifier,
    player1Won: Boolean,
    isDraw: Boolean,
    gameViewModel: GameViewModel
) {
    @StringRes val gameResult: Int = if (isDraw) {
        R.string.draw
    } else if (player1Won) {
        R.string.player1_won
    } else {
        R.string.player2_won
    }
    val activity = (LocalContext.current as Activity)
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = stringResource(R.string.congratulations)) },
        text = { Text(text = stringResource(gameResult)) },
        dismissButton = {
            TextButton(onClick = { activity.finish() }) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = { gameViewModel.NewGame() }) {
                Text(text = stringResource(R.string.play_again))
            }
        })


}


@Composable
fun CurrentScore(
    modifier: Modifier = Modifier,
    player1Score: Int,
    player2Score: Int,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Card(
            modifier = Modifier
                .size(50.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE25A50)
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
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
            modifier = Modifier.size(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2580DB)
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
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
    modifier: Modifier = Modifier,
    isPlayer1Turn: Boolean
) {
    if (isPlayer1Turn) {
        Text(
            text = stringResource(R.string.player_1_turn),
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Red
        )
    } else {
        Text(
            text = stringResource(R.string.player_2_turn),
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Blue
        )
    }

}

@Composable
fun TicTacToeZeroIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    strokeWidth: Dp = 2.dp
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val circleRadius = min(canvasWidth, canvasHeight) / 2
        val circleCenter = Offset(x = canvasWidth / 2, y = canvasHeight / 2)
        drawCircle(
            color = color,
            center = circleCenter,
            radius = circleRadius - strokeWidth.value / 2,
            style = Stroke(width = strokeWidth.value)
        )
    }
}


@Composable
fun TickTacToeButton(
    modifier: Modifier = Modifier,
    boxNumber: Int,
    onBoxClicked: (Int) -> Unit,
    currentIconToDisplay: String,
    isGameOver: Boolean
) {
    Button(modifier = modifier
        .padding(5.dp)
        .size(65.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(10.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            if (!isGameOver) onBoxClicked(boxNumber)
        }
    )
    {
        if (currentIconToDisplay == "Cross") {
            Icon(

                imageVector = Icons.Filled.Clear,
                contentDescription = "Cross",
                tint = Color.Red,
                modifier = Modifier.size(64.dp)
            )
        } else if (currentIconToDisplay == "Zero") {
            TicTacToeZeroIcon(
                color = Color.Blue,
                modifier = Modifier.size(44.dp),
                strokeWidth = 15.dp
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TickTacToeTopBar(
    modifier: Modifier = Modifier,
    gameUiState: GameUiState,
    gameViewModel: GameViewModel
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = stringResource(R.string.tic_tac_toe),
                    style = MaterialTheme.typography.displaySmall
                )

            }
        },
        actions = {
            lateinit var iconToDisplay: ImageVector
            val iconColor: Color
            if (gameUiState.isDarkTheme) {
                iconToDisplay = Icons.Filled.DarkMode
                iconColor = MaterialTheme.colorScheme.primary
            }

            else {
                iconToDisplay = Icons.Filled.LightMode
                iconColor = Color(0xFFFFC107)
            }

            Switch(
                checked = gameUiState.isDarkTheme,
                onCheckedChange = { gameViewModel.ChangeTheme() },
                thumbContent = {
                    Icon(
                        imageVector = iconToDisplay,
                        contentDescription = stringResource(R.string.dark_theme_button),
                        tint = iconColor
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TickTacToeScreenPreview() {
    TicTacToeTheme {

    }
}