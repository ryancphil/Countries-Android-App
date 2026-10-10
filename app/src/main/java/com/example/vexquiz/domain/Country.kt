package com.example.vexquiz.domain

data class Country(
    val name: String,
    val capitals: List<String>,
    val flag: Flag
)

data class Flag(
    val emoji: String,
    val url: String
)
