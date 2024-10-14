package com.inad.pokemongame.data

import com.inad.pokemongame.model.Pokemon
import com.inad.pokemongame.network.PokemonApiService

interface PokemonRepository {
    suspend fun getPokemons(): List<Pokemon>
}

val TAG = "PokemonRepository"

class NetworkPokemonRepository(
    private val pokemonApiService: PokemonApiService
) : PokemonRepository {
    override suspend fun getPokemons(): List<Pokemon> {
        val pokemons = pokemonApiService.getPokemons().results ?: listOf()
        if (pokemons.isNotEmpty()) {
            pokemons.forEach { pokemon ->
                val id = pokemon.url.split("/".toRegex()).dropLast(1).last()
                pokemon.id = id.toInt()
            }
        }
        return pokemons
    }

}