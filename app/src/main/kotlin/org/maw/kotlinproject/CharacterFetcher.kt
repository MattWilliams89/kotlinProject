package org.maw.kotlinproject


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

        // Create an instance of our GitHub API interface.
        val characterFetcher = restAdapter.create<CharacterList>(javaClass<CharacterList>())

        val parameters = getRequestParameters();
        parameters.put("offset", currentCount)

        // Fetch and print a list of the contributors to this library.
        characterFetcher.characters(parameters, listener)
    }
}
