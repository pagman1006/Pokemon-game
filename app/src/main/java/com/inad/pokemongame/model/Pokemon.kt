package com.inad.pokemongame.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Pokemon(
    @Transient
    var id: Int=0,
    val name: String,
    val url: String,
)