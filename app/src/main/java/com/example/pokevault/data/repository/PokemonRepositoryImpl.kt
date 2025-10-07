package com.example.pokevault.data.repository

import com.example.pokevault.data.local.dao.PokemonDao
import com.example.pokevault.data.local.datastore.AppPreferencesDataStore
import com.example.pokevault.data.mapper.toDomain
import com.example.pokevault.data.mapper.toEntity
import com.example.pokevault.data.network.PokeApiService
import com.example.pokevault.domain.entities.Pokemon
import com.example.pokevault.domain.entities.PokemonList
import com.example.pokevault.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApiService: PokeApiService,
    private val pokemonDao: PokemonDao,
    private val appPreferences: AppPreferencesDataStore
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonList> {
        return try {
            // First, check if the cache is still valid
            val isCacheValid = appPreferences.isCacheValid()

            // Get data from the database
            val localPage = pokemonDao.getPokemonPagedOnce(limit, offset)

            if (isCacheValid && localPage.isNotEmpty()) {
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

            val response = pokeApiService.getPokemonList(limit, offset)
            val domain = response.toDomain()

            pokemonDao.deleteAll()
            val entities = domain.results.map { it.toEntity() }
            pokemonDao.insertAll(entities)
            appPreferences.updateLastApiCallTime()

            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonById(id: Int): Result<Pokemon> {
        return try {
            // Get data from the database
            val local = pokemonDao.getPokemonByIdOnce(id)
            if (local != null) {
                return Result.success(local.toDomain())
            }

            val response = pokeApiService.getPokemonById(id)
            val domain = response.toDomain()
            pokemonDao.insert(domain.toEntity())
            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
