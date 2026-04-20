package com.pdm0126.taller1_00097524.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pdm0126.taller1_00097524.R
import com.pdm0126.taller1_00097524.TOTAL_QUESTIONS
import com.pdm0126.taller1_00097524.data.QuizQuestions

@Composable
fun QuestionsScreen(
    currentIndex: Int,
    score: Int,
    onNextClick: () -> Unit,
    onCorrect: () -> Unit
){
    val currentQuestion = QuizQuestions[currentIndex]
    var optionSelected by rememberSaveable { mutableStateOf<String?>(null) }
    val hasAnswered = optionSelected != null
    val scrollState = rememberScrollState()

    fun optionSelection(option: String) {
        if(!hasAnswered){
            optionSelected = option

            if(option == currentQuestion.correctAnswer){
                onCorrect()
            }
        }
    }

//   Column que me expande a todo el espacio de pantalla
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // row para mostrar la pregunta actual y el puntaje actual
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Box(){ Text("Pregunta ${currentIndex + 1} de  ${TOTAL_QUESTIONS}")}
            Box(){ Text("Puntaje: ${score} / ${TOTAL_QUESTIONS} ")}
        }
        // column para limitar el espacio en pantalla
        Column(
            modifier = Modifier.fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Enunciado de la pregunta
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(currentQuestion.question)
            }

            Spacer(Modifier.height(20.dp))

            // Logica para mostrar botones de respuesta

            currentQuestion.options.forEach { option ->

                val buttonColor = when {
                    optionSelected == null -> colorResource(R.color.Marron)

                    option == currentQuestion.correctAnswer -> Color.Green

                    option == optionSelected -> colorResource(R.color.Red)

                    else -> Color.Gray
                }

                Button(
                    onClick = {optionSelection(option)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor,
                        contentColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = option)
                }
                Spacer(Modifier.height(8.dp))
            }

            Spacer(Modifier.height(20.dp))
            if(hasAnswered) {
                // Logica de FunFact
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                colorResource(R.color.Orange),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "FunFact: ", color = Color.White, textAlign = TextAlign.Justify)
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .border(
                                width = 4.dp,
                                colorResource(R.color.Orange),
                                shape = RoundedCornerShape(10.dp)
                            ).padding(10.dp),
                    ) {
                        Text(
                            currentQuestion.funFact,
                            color = Color.Black,
                            textAlign = TextAlign.Justify
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))
                //button para pasar a siguiente pregunta
                Button(
                    onClick = {
                        optionSelected = null
                        onNextClick()
                    },
                    enabled = hasAnswered
                ) {
                    Text("Siguiente")
                }
            }
        }

    }}

@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview(){
    QuestionsScreen( currentIndex = 0, score = 0, onNextClick = {}, onCorrect = {})
}