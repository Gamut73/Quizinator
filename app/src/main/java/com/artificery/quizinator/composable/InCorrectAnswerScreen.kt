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

@Composable
fun InCorrectAnswerScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val quizState = SharedPrefManager.getQuizState()!!
        Text(text = "Both contestants answered incorrectly. The correct answer was:\n${quizState.questionAnswers.get(quizState.currentQuestionIndex).answer}")

        Button(onClick = {
            quizState.currentQuestionIndex++
            SharedPrefManager.saveQuizState(quizState)

            navController.navigate("questionScreen")
        }) {
            Text(text = "Next")
        }
    }
}