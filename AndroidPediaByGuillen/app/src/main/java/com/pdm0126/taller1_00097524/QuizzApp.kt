package com.pdm0126.taller1_00097524

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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

@Composable
fun ResultScreen (
    score: Int,
    onNextClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "El puntaje final que obtuiste ${score} de ${TOTAL_QUESTIONS} ")

        Spacer(Modifier.height(12.dp))

        val scoreMessage = when {
            score == 1 -> "Aun debes repasar mas, pero al menos lograste una respuesta buena "

            score == 2 -> "Muy bien, vas por buen camino, estudia un poco mas y estaras excelente"

            score == 3 -> "Felicidadeeees, obtuviste puntuacion perfecto, no detengas el buen trabajo y sigue asi"

            else -> "No te decepciones por tu nota, la proxima saldras aun mejor solo debes estudiar mas"
        }

        Box(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = " ${scoreMessage}",
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(12.dp))

        Button(
            modifier = Modifier.height(50.dp),
            onClick = onNextClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.Teal),
                contentColor = colorResource(R.color.white)
            )
        ) {
            Text(
                "Reinicia Quizz",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
            )
        }
    }

    }



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

@Composable
fun WelcomeScreen(
    onNextClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AndroidPedia",
            fontSize = 48.sp,
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.Red)

        )
        Text(
            text = "¿Cuanto Sabes de Android",
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            color = colorResource(R.color.Marron    )
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Samuel-00097524",
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            color = colorResource(R.color.Marron)
        )
        Spacer(Modifier.height(12.dp))
        Button(
            modifier = Modifier.height(50.dp),
            onClick = onNextClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.Teal),
                contentColor = colorResource(R.color.white)
            )
        ){
            Text("Comenzar Quizz",
            fontSize = 20.sp,
                fontFamily = FontFamily.Serif)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview(){
    ResultScreen ( score=0, onNextClick = {})
}

@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview(){
    QuestionsScreen( currentIndex = 0, score = 0, onNextClick = {}, onCorrect = {})
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen(onNextClick = {})
}

@Preview(showBackground = true)
@Composable
fun QuizzAppPreview(){
    QuizzApp()
}