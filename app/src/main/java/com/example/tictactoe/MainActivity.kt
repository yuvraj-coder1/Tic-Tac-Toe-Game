package com.example.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.TicTacToeTheme

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
fun TickTacToeGameLayout(modifier: Modifier=Modifier) {
    Column {
        CurrentScore()
        Spacer(modifier = modifier.size(20.dp))
       CurrentPlayerTurn()
        Spacer(modifier = modifier.size(20.dp))
        Column(
            modifier=Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Row {
                TickTacToeButton(modifier = Modifier)
                TickTacToeButton(modifier = Modifier)
                TickTacToeButton(modifier = Modifier)
            }
            Row {
                TickTacToeButton()
                TickTacToeButton()
                TickTacToeButton()
            }
            Row {
                TickTacToeButton()
                TickTacToeButton()
                TickTacToeButton()
            }
        }
    }
}

@Composable
fun CurrentScore(modifier: Modifier=Modifier)
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
                   text = "1",
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

                   text = "1",
                   textAlign = TextAlign.Center
               )
           }
       }
   }
}

@Composable
fun CurrentPlayerTurn(modifier: Modifier=Modifier)
{
    Text(
        text = "Player 1 turn",
        style = MaterialTheme.typography.headlineLarge,
        modifier=modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TickTacToeButton(modifier: Modifier=Modifier) {

    Button(onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
        modifier=modifier.padding(5.dp)
            .size(65.dp)
    )
        {

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
                   text = "Tic Tac Toe",
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