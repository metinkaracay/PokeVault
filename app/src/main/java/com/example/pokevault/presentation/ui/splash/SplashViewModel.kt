package com.example.pokevault.presentation.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokevault.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "SplashViewModel"
    }

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        viewModelScope.launch {
            getPokemonListUseCase()
                .onSuccess { pokemonList ->
                    Log.d(TAG, "Pokemon listesi başarıyla yüklendi!")
                    Log.d(TAG, "Toplam Pokemon sayısı: ${pokemonList.count}")
                    Log.d(TAG, "Gelen Pokemon sayısı: ${pokemonList.results.size}")
                    
                    pokemonList.results.forEachIndexed { index, pokemon ->
                        Log.d(TAG, "Pokemon ${index + 1}: ID=${pokemon.id}, Name=${pokemon.name}, Image=${pokemon.imageUrl}")
                    }
                    
                    if (pokemonList.next != null) {
                        Log.d(TAG, "Sonraki sayfa mevcut: ${pokemonList.next}")
                    }
                }
                .onFailure { exception ->
                    Log.e(TAG, "Pokemon listesi yüklenirken hata oluştu: ${exception.message}")
                    exception.printStackTrace()
                }
        }
    }
}
