package com.example.learningapis

data class MySecData(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)