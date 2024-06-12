package com.artificery.quizinator.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
fun InCorrectAnswerScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val quizState = SharedPrefManager.getQuizState()!!
        Text(text = "Both contestants answered incorrectly. The correct answer was:\n",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp))

        Text(text = quizState.questionAnswers.get(quizState.currentQuestionIndex).answer,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp))

        Button(onClick = {
            quizState.currentQuestionIndex++
            SharedPrefManager.saveQuizState(quizState)

            navController.navigate("questionScreen")
        }) {
            Text(text = "Next")
        }
    }
}