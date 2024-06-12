package com.artificery.quizinator.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        Text(text = "Game Over!", fontSize = 32.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp))

        Text(text = " Final Scores:",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp))


        Text(text = "Red: ${quizState.redScore}\n" +
                "Blue: ${quizState.blueScore}",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp))

        val finalMessage = if (quizState.redScore == quizState.blueScore) {
            "It's a tie!"
        } else {
            val winner = if (quizState.redScore > quizState.blueScore) "Red" else "Blue"
            "The winner is: $winner"
        }
        Text(text = finalMessage,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp))
    }
}
