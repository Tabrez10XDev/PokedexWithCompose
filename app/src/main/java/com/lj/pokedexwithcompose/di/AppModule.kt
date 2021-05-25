package com.lj.pokedexwithcompose.di

import com.lj.pokedexwithcompose.data.remote.PokeApi
import com.lj.pokedexwithcompose.repositories.PokemonRepository
import com.lj.pokedexwithcompose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providePokemonRepository(
        api : PokeApi
    ) = PokemonRepository(api)



    @Singleton
    @Provides
    fun providePokeApi() : PokeApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeApi::class.java)
    }

}