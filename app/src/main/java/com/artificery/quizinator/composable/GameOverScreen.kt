package com.artificery.quizinator.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.artificery.quizinator.SharedPrefManager

@Composable
fun GameOverScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val quizState = SharedPrefManager.getQuizState()!!

        if (quizState.redScore == quizState.blueScore) {
            Text(text = "Game Over!\n" +
                    " Final Scores:\n" +
                    "Red: ${quizState.redScore}\n" +
                    "Blue: ${quizState.blueScore}" +
                    "It's a tie!")
        } else {
            val winner = if (quizState.redScore > quizState.blueScore) "Red" else "Blue"
            Text(text = "Game Over!\n Final Scores:\nRed: ${quizState.redScore}\nBlue: ${quizState.blueScore}" +
                    "The winner is: $winner")
        }

    }
}
