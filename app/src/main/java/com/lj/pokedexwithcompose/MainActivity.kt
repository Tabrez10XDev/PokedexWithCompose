package com.lj.pokedexwithcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.lj.pokedexwithcompose.ui.pokemondetail.PokemonDetailScreen
import com.lj.pokedexwithcompose.ui.pokemonlist.PokemonListScreen
import com.lj.pokedexwithcompose.ui.theme.PokedexWIthComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexWIthComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "pokemon_list_screen"
                ) {
                    composable("pokemon_list_screen") {
                        PokemonListScreen(navController = navController)
                    }
                    composable(
                        "pokemon_detail_screen/{dominantColour}/{pokemonName}",
                        arguments = listOf(
                            navArgument("dominantColour") {
                                type = NavType.IntType
                            },
                            navArgument("pokemonName") {
                                type = NavType.StringType
                            }
                        )
                    ){
                        val dominantColour = remember{
                          val colour = it.arguments?.getInt("dominantColour")
                          colour?.let { Color(it) } ?: Color.White
                            }
                        val pokemonName = remember{
                            it.arguments?.getString("pokemonName")
                        }

                        PokemonDetailScreen(
                            dominantColour = dominantColour,
                            pokemonName = pokemonName?.toLowerCase(Locale.ROOT) ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

