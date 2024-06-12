package com.artificery.quizinator.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.artificery.quizinator.SharedPrefManager
import com.artificery.quizinator.enums.AnswerMode
import com.artificery.quizinator.enums.Contestant
import com.artificery.quizinator.model.QuestionAnswer
import com.artificery.quizinator.model.QuizState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun SecondScreen(navController: NavController) {
    val jsonString = navController.currentBackStackEntry?.arguments?.getString("questionAnswers")
    val listType = object : TypeToken<List<QuestionAnswer>>() {}.type
    val questionAnswers = Gson().fromJson<List<QuestionAnswer>>(jsonString, listType)

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
            Text(text = "Welcome", fontWeight = FontWeight.Bold, fontSize = 36.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Begin the when you a ready.", fontWeight = FontWeight.Normal, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val quizState = initQuizState(questionAnswers)
                SharedPrefManager.saveQuizState(quizState)

                navController.navigate("questionScreen")
            }) {
                Text(text = "Begin")
            }

        }
    }
}

private fun initQuizState(questionAnswers: List<QuestionAnswer>): QuizState {
    return QuizState(
        questionAnswers = questionAnswers,
        currentQuestionIndex = 0,
        redScore = 0,
        blueScore = 0,
        currentAnswerer = Contestant.NEUTRAL,
        answerMode = AnswerMode.NORMAL
    )
}