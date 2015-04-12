package org.maw.kotlinproject.RestAdapters

import org.maw.kotlinproject.BASE_URL
import org.maw.kotlinproject.Models.Result
import org.maw.kotlinproject.getRequestParameters
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.http.GET
import retrofit.http.QueryMap

public class CharacterFetcher {

    trait CharacterList {
        GET("/v1/public/characters")
        public fun characters(QueryMap params: Map<String, String>, callback: Callback<Result>)
    }

    public fun fetchAllCharacters(currentCount: String, listener: Callback<Result>) {
        val restAdapter = RestAdapter.Builder().setEndpoint(BASE_URL).build()

        val characterFetcher = restAdapter.create<CharacterList>(javaClass<CharacterList>())

        val parameters = getRequestParameters();
        parameters.put("offset", currentCount)

        characterFetcher.characters(parameters, listener)
    }
}