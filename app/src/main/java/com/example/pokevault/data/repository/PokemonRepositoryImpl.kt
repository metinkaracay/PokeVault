package com.example.pokevault.data.repository

import com.example.pokevault.data.local.dao.PokemonDao
import com.example.pokevault.data.mapper.toDomain
import com.example.pokevault.data.mapper.toEntity
import com.example.pokevault.data.network.PokeApiService
import com.example.pokevault.domain.entities.Pokemon
import com.example.pokevault.domain.entities.PokemonList
import com.example.pokevault.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApiService: PokeApiService,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonList> {
        return try {
            // Try fetch the page from DB first
            val localPage = pokemonDao.getPokemonPagedOnce(limit, offset)
            if (localPage.isNotEmpty()) {
                val domainList = localPage.map { it.toDomain() }
                return Result.success(
                    PokemonList(
                        count = pokemonDao.countAll(),
                        next = null,
                        previous = null,
                        results = domainList
                    )
                )
            }

            // If DB is empty, fetch from API, save to DB and return
            val response = pokeApiService.getPokemonList(limit, offset)
            val domain = response.toDomain()

            // Only save the incoming page
            val entities = domain.results.map { it.toEntity() }
            pokemonDao.insertAll(entities)

            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonById(id: Int): Result<Pokemon> {
        return try {
            // Try DB first
            val local = pokemonDao.getPokemonByIdOnce(id)
            if (local != null) {
                return Result.success(local.toDomain())
            }

            // Retrieve from API and save to DB
            val response = pokeApiService.getPokemonById(id)
            val domain = response.toDomain()
            pokemonDao.insert(domain.toEntity())
            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
