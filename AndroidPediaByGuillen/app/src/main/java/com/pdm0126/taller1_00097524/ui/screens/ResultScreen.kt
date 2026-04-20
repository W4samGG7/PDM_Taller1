package com.pdm0126.taller1_00097524.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm0126.taller1_00097524.R
import com.pdm0126.taller1_00097524.TOTAL_QUESTIONS

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
        Text(text = "El puntaje final que obtuviste ${score} de ${TOTAL_QUESTIONS} ")

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

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview(){
    ResultScreen ( score=0, onNextClick = {})
}
