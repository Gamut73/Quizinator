package com.artificery.quizinator.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.artificery.quizinator.SharedPrefManager
import com.artificery.quizinator.enums.Contestant

@Composable
fun CorrectAnswerScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val quizState = SharedPrefManager.getQuizState()!!
        Text(text = "Correct! 1 Point to ${quizState.currentAnswerer}")

        Button(onClick = {
            if (quizState.currentAnswerer == Contestant.RED) {
                quizState.redScore += 1
            } else {
                quizState.blueScore += 1
            }

            quizState.currentQuestionIndex++
            SharedPrefManager.saveQuizState(quizState)

            navController.navigate("questionScreen")
        }) {
            Text(text = "Next")
        }
    }
}