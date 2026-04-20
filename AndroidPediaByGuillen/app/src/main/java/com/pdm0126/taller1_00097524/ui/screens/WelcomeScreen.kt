package com.pdm0126.taller1_00097524.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm0126.taller1_00097524.R

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
fun WelcomeScreenPreview(){
    WelcomeScreen(onNextClick = {})
}
