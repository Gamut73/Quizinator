package com.artificery.quizinator.model

import android.os.Parcelable
import com.artificery.quizinator.enums.AnswerMode
import com.artificery.quizinator.enums.Contestant
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class QuizState(
    val questionAnswers: List<QuestionAnswer>,
    var currentQuestionIndex: Int,
    var redScore: Int,
    var blueScore: Int,
    var currentAnswerer: Contestant,
    var answerMode: AnswerMode
) : Parcelable
