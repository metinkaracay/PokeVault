package com.example.pokevault.data.dto

import com.google.gson.annotations.SerializedName

data class PokemonDto(
    val id: Int,
    val name: String,
    val url: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val sprites: Sprites? = null,
    val types: List<TypeSlot>? = null,
    val abilities: List<AbilitySlot>? = null
)

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String? = null,
    @SerializedName("other")
    val other: OtherSprites? = null
)

data class OtherSprites(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork? = null
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String? = null
)

data class TypeSlot(
    val type: Type
)

data class Type(
    val name: String
)

data class AbilitySlot(
    val ability: Ability
)

data class Ability(
    val name: String
)
