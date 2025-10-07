package com.example.pokevault.domain.usecase

import com.example.pokevault.domain.entities.PokemonList
import com.example.pokevault.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int = 20, offset: Int = 0): Result<PokemonList> {
        return pokemonRepository.getPokemonList(limit, offset)
    }
}
