package com.artificery.quizinator.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionAnswer(
    val question: String,
    val answer: String
) : Parcelable
