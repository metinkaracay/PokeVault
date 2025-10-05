package com.example.pokevault.data.mapper

import com.example.pokevault.data.dto.PokemonDto
import com.example.pokevault.data.dto.PokemonListDto
import com.example.pokevault.data.dto.PokemonListItemDto
import com.example.pokevault.domain.entities.Pokemon
import com.example.pokevault.domain.entities.PokemonList

fun PokemonDto.toDomain(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        url = url ?: "",
        imageUrl = sprites?.other?.officialArtwork?.frontDefault 
            ?: sprites?.frontDefault 
            ?: "",
        height = height,
        weight = weight,
        types = types?.map { it.type.name } ?: emptyList(),
        abilities = abilities?.map { it.ability.name } ?: emptyList()
    )
}

fun PokemonListDto.toDomain(): PokemonList {
    return PokemonList(
        count = count,
        next = next,
        previous = previous,
        results = results.map { it.toDomain() }
    )
}

fun PokemonListItemDto.toDomain(): Pokemon {
    // URL'den ID'yi çıkar: "https://pokeapi.co/api/v2/pokemon/1/" -> 1
    val id = url.split("/").dropLast(1).lastOrNull()?.toIntOrNull() ?: 0
    
    return Pokemon(
        id = id,
        name = name,
        url = url,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    )
}
