package com.artificery.quizinator.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.artificery.quizinator.SharedPrefManager
import com.artificery.quizinator.enums.AnswerMode
import com.artificery.quizinator.enums.Contestant

@Composable
fun AnswerScreen(navController: NavController, context: Context) {
    val quizState = SharedPrefManager.getQuizState()!!

    val answer = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${quizState.currentAnswerer}, please type your answer below")

        OutlinedTextField(
            value = answer.value,
            onValueChange = { answer.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )

        Button(onClick = {
            if (answer.value.isEmpty()) {
                Toast.makeText(context, "Please enter an answer", Toast.LENGTH_SHORT).show()
                return@Button
            }

            if (answerIsCorrect(answer.value, quizState.questionAnswers.get(quizState.currentQuestionIndex).answer)) {
                navController.navigate("correctAnswerScreen")
            } else {

                if (quizState.answerMode == AnswerMode.NORMAL) {
                    quizState.currentAnswerer = if (quizState.currentAnswerer == Contestant.RED) Contestant.BLUE else Contestant.RED
                    quizState.answerMode = AnswerMode.STEAL
                    SharedPrefManager.saveQuizState(quizState)
                    Toast.makeText(context, "Incorrect! " + quizState.currentAnswerer.toString() + " has a chance to steal", Toast.LENGTH_SHORT).show()
                    navController.navigate("answerScreen")
                } else {
                    navController.navigate("inCorrectAnswerScreen")
                }
            }
        }) {
            Text(text = "Check Answer")
        }
    }
}
private fun answerIsCorrect(givenAnswer: String, actualAnswer: String): Boolean {
    return actualAnswer.equals(givenAnswer, ignoreCase = true)
}

