package com.lj.pokedexwithcompose.repositories

import com.lj.pokedexwithcompose.data.remote.PokeApi
import com.lj.pokedexwithcompose.data.remote.responses.Pokemon
import com.lj.pokedexwithcompose.data.remote.responses.PokemonList
import com.lj.pokedexwithcompose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api : PokeApi
){

    suspend fun getPokemonList(limit: Int, offset: Int) : Resource<PokemonList>{
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e : Exception){
            return Resource.error("An unknown error occured", null)
        }
        return Resource.success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String) : Resource<Pokemon>{
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e : Exception){
            return Resource.error("An unknown error occured", null)
        }
        return Resource.success(response)
    }

}