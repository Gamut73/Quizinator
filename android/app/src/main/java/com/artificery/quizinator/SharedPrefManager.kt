package com.artificery.quizinator

import android.content.Context
import android.content.SharedPreferences
import com.artificery.quizinator.model.QuizState
import com.google.gson.Gson

object SharedPrefManager {
    private const val PREF_NAME = "quizinator_pref"
    private const val QUIZ_STATE_KEY = "quiz_state"
    private var sharedPreferences: SharedPreferences? = null
    private val gson = Gson()

    fun init(context: Context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    fun saveQuizState(quizState: QuizState) {
        val editor = sharedPreferences?.edit()
        val quizStateJson = gson.toJson(quizState)
        editor?.putString(QUIZ_STATE_KEY, quizStateJson)
        editor?.apply()
    }

    fun getQuizState(): QuizState? {
        val quizStateJson = sharedPreferences?.getString(QUIZ_STATE_KEY, null)
        return if (quizStateJson != null) gson.fromJson(quizStateJson, QuizState::class.java) else null
    }
}