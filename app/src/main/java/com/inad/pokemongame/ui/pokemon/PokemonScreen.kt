package com.inad.pokemongame.ui.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.inad.pokemongame.R
import com.inad.pokemongame.ui.theme.PokemonGameTheme


@Composable
fun PokemonGameScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { PokemonTopAppBar() }
    ) {
        val pokemonViewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.Factory)
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PokemonScreen(pokemonViewModel.pokemonUiState, contentPadding = it)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
            )
        }, modifier = modifier,
        //colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        //    containerColor = MaterialTheme.colorScheme.primary,
        //    titleContentColor = MaterialTheme.colorScheme.onPrimary
        //)
    )
}

@Composable
fun PokemonScreen(
    pokemonUiState: PokemonUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        modifier = modifier
            .padding(
                top = contentPadding.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(R.string.who_is),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.fillMaxWidth()
        )
        PokemonImage()
        PokemonOptions()
    }

}

@Composable
fun PokemonImage(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
                .crossfade(true)
                .build(),
            contentDescription = "Pokemon",
            contentScale = ContentScale.Fit,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PokemonOptions() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonOption("Bulbasaur")
        PokemonOption("Charmander")
        PokemonOption("Squirtle")
        PokemonOption("Pikachu")
    }
}

@Composable
fun PokemonOption(name: String) {
    Button(onClick = { /*TODO*/ }) {
        Text(name)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PokemonScreenPreview() {
    PokemonGameTheme {
        PokemonGameScreen()
    }
}