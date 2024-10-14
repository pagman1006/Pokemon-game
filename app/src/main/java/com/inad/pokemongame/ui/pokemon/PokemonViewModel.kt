package com.inad.pokemongame.ui.pokemon

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.inad.pokemongame.PokemonGameApplication
import com.inad.pokemongame.model.Pokemon
import com.inad.pokemongame.data.PokemonRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Arrays.asList

sealed interface PokemonUiState {
    data class Success(val pokemons: List<Pokemon>) : PokemonUiState
    object Error : PokemonUiState
    object Loading : PokemonUiState
}

private const val TAG = "PokemonViewModel"

class PokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var pokemonUiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
        private set


    /**
     * Call getPokemons() on init so we can display status immediately.
     */
    init {
        getPokemons()
    }

    /**
     * Gets Pokemon information from the Pokemon API Retrofit service and updates the
     * [Pokemon] [List] [MutableList].
     */
    fun getPokemons() {
        viewModelScope.launch {
            pokemonUiState = PokemonUiState.Loading
            pokemonUiState = try {
                PokemonUiState.Success(pokemonRepository.getPokemons())
            } catch (e: IOException) {
                PokemonUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokemonGameApplication)
                val pokemonRepository = application.container.pokemonRepository
                PokemonViewModel(pokemonRepository = pokemonRepository)
            }
        }
    }
}