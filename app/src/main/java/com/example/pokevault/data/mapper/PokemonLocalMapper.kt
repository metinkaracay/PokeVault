package com.example.pokevault.data.mapper

import com.example.pokevault.data.local.entity.PokemonEntity
import com.example.pokevault.domain.entities.Pokemon

fun PokemonEntity.toDomain(): Pokemon =
    Pokemon(
        id = id,
        name = name,
        url = url,
        imageUrl = imageUrl,
        height = height,
        weight = weight,
        types = types,
        abilities = abilities
    )

fun Pokemon.toEntity(): PokemonEntity =
    PokemonEntity(
        id = id,
        name = name,
        url = url,
        imageUrl = imageUrl,
        height = height,
        weight = weight,
        types = types,
        abilities = abilities
    )


