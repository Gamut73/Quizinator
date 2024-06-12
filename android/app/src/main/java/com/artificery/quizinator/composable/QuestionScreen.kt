package com.artificery.quizinator.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.artificery.quizinator.SharedPrefManager
import com.artificery.quizinator.enums.AnswerMode
import com.artificery.quizinator.enums.Contestant

@Composable
fun QuestionScreen(navController: NavController) {
    val quizState = SharedPrefManager.getQuizState()!!

    if (quizState.currentQuestionIndex >= quizState.questionAnswers.size) {
        navController.navigate("gameOverScreen")
    } else {
        val squareShape = RoundedCornerShape(0.dp)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    gotoAnswerScreen(navController, Contestant.RED)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = squareShape
            ) {
                Text(text = quizState.redScore.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp)
            }
            Text(
                text = quizState.questionAnswers.get(quizState.currentQuestionIndex).question,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    gotoAnswerScreen(navController, Contestant.BLUE)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = squareShape
            ) {
                Text(text = quizState.blueScore.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp)
            }
        }
    }
}

private fun gotoAnswerScreen(navController: NavController, firstToBuzz: Contestant) {
    val quizState = SharedPrefManager.getQuizState()!!
    quizState.currentAnswerer = firstToBuzz
    quizState.answerMode = AnswerMode.NORMAL
    SharedPrefManager.saveQuizState(quizState)

    navController.navigate("answerScreen")
}