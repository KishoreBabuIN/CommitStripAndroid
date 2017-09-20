package com.kishorebabu.android.commitstrip.data.remote


import com.kishorebabu.android.commitstrip.data.model.Pokemon
import com.kishorebabu.android.commitstrip.data.model.PokemonListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MvpStarterService {

    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int): Single<PokemonListResponse>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Single<Pokemon>

}
