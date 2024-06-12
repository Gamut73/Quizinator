package com.artificery.quizinator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artificery.quizinator.composable.AnswerScreen
import com.artificery.quizinator.composable.CorrectAnswerScreen
import com.artificery.quizinator.composable.GameOverScreen
import com.artificery.quizinator.composable.InCorrectAnswerScreen
import com.artificery.quizinator.composable.QuestionScreen
import com.artificery.quizinator.composable.SecondScreen

class MainActivity : ComponentActivity() {
    private var message = mutableStateOf("Pick a Json file to load the quiz from")
    private lateinit var navController: NavController;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPrefManager.init(this);

        setContent {
            navController = rememberNavController()

            NavHost(navController = navController as NavHostController, startDestination = "entryPage") {
                composable("entryPage") {
                    EntryPageMessageCard(navController, message = message.value)
                }
                composable("secondScreen/{questionAnswers}") {
                    SecondScreen(navController)
                }
                composable("questionScreen") {
                    QuestionScreen(navController)
                }
                composable("answerScreen") {
                    AnswerScreen(navController, this@MainActivity)
                }
                composable("correctAnswerScreen") {
                    CorrectAnswerScreen(navController)
                }
                composable("inCorrectAnswerScreen") {
                    InCorrectAnswerScreen(navController)
                }
                composable("gameOverScreen") {
                    GameOverScreen(navController)
                }
            }
        }
    }


    @Composable
    fun EntryPageMessageCard(navController: NavController, message: String) {
        Column (modifier = Modifier.padding(16.dp)) {
            Text(text = message, fontWeight = FontWeight.Bold, fontSize = 36.sp,
                modifier = Modifier.clickable {
                    getContent.launch("application/*")
                })
        }
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        val inputStream = contentResolver.openInputStream(uri!!)
        val jsonString = inputStream?.bufferedReader().use { it?.readText() }

        getNavController().navigate("secondScreen/$jsonString")
    }

    private fun getNavController(): NavController {
        return navController
    }
}