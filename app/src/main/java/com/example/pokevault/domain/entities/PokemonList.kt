package com.example.pokevault.domain.entities

data class PokemonList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
