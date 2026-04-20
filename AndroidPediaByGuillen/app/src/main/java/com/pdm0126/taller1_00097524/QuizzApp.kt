package com.pdm0126.taller1_00097524

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pdm0126.taller1_00097524.ui.screens.ResultScreen
import com.pdm0126.taller1_00097524.ui.screens.QuestionsScreen
import com.pdm0126.taller1_00097524.ui.screens.WelcomeScreen

const val TOTAL_QUESTIONS = 3
@Composable
fun QuizzApp(modifier: Modifier = Modifier){
    var currentQuestion by rememberSaveable { mutableIntStateOf(0) }
    var currentView by rememberSaveable {mutableIntStateOf(1) }
    var currentScore by rememberSaveable {mutableIntStateOf(0) }

    fun onButtonClick(){
        when (currentView){
            1 -> currentView ++
            2 -> {
                currentQuestion++
                if(currentQuestion >= TOTAL_QUESTIONS){
                    currentView++
                }
            }
            3-> {
                currentView=2
                currentQuestion=0
                currentScore=0
            }
            else -> currentView = 1
        }
    }

    Box(modifier = modifier) {
        when (currentView) {
            1 -> WelcomeScreen(onNextClick = {onButtonClick()})
            2 -> QuestionsScreen(
                currentIndex = currentQuestion, score = currentScore,
                onNextClick = {onButtonClick()},
                onCorrect = { currentScore++ })

            3 -> ResultScreen(score = currentScore, onNextClick = {onButtonClick()})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizzAppPreview(){
    QuizzApp()
}