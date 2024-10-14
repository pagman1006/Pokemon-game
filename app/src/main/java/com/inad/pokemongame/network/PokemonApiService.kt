package com.inad.pokemongame.network

import com.inad.pokemongame.model.Response
import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokemon/?limit=151")
    suspend fun getPokemons(): Response
}