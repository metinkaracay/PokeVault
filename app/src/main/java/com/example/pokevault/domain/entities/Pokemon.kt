package com.example.pokevault.domain.entities

data class Pokemon(
    val id: Int,
    val name: String,
    val url: String,
    val imageUrl: String,
    val height: Int? = null,
    val weight: Int? = null,
    val types: List<String> = emptyList(),
    val abilities: List<String> = emptyList()
)
