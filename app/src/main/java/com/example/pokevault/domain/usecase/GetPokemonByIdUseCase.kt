package com.example.pokevault.domain.usecase

import com.example.pokevault.domain.entities.Pokemon
import com.example.pokevault.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonByIdUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Result<Pokemon> {
        return pokemonRepository.getPokemonById(id)
    }
}
