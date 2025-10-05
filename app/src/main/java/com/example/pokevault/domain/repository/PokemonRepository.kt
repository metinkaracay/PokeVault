package com.example.pokevault.domain.repository

import com.example.pokevault.domain.entities.Pokemon
import com.example.pokevault.domain.entities.PokemonList

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonList>
    suspend fun getPokemonById(id: Int): Result<Pokemon>
}
