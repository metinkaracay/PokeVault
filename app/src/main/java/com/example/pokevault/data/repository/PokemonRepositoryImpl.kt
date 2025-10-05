package com.example.pokevault.data.repository

import com.example.pokevault.data.mapper.toDomain
import com.example.pokevault.data.network.PokeApiService
import com.example.pokevault.domain.entities.Pokemon
import com.example.pokevault.domain.entities.PokemonList
import com.example.pokevault.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApiService: PokeApiService
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonList> {
        return try {
            val response = pokeApiService.getPokemonList(limit, offset)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonById(id: Int): Result<Pokemon> {
        return try {
            val response = pokeApiService.getPokemonById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
