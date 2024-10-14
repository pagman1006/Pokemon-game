package com.inad.pokemongame

import android.app.Application
import com.inad.pokemongame.data.AppContainer
import com.inad.pokemongame.data.DefaultAppContainer

class PokemonGameApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}