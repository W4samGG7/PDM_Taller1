package com.pdm0126.taller1_00097524.model

data class Question(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val funFact: String
)