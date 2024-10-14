package com.inad.pokemongame.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(

    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>?
)
