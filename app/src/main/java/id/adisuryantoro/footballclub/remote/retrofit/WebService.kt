package id.adisuryantoro.footballclub.remote.retrofit

import id.adisuryantoro.footballclub.BuildConfig
import id.adisuryantoro.footballclub.model.Club
import id.adisuryantoro.footballclub.model.League
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("api/v1/json/1/all_leagues.php")
    fun requestLeague(): Call<League>

    @GET("api/v1/json/1/search_all_teams.php")
    fun readClubList(@Query("l") leagueName: String): Call<Club>

    @GET("api/v1/json/1/searchteams.php")
    fun readSearchClub(@Query("t") keyword: String): Call<Club>

    @GET("api/v1/json/1/lookupteam.php")
    fun readClubDetail(@Query("id") id: String): Call<Club>

    companion object Factory {
        fun create(): WebService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WebService::class.java)
        }
    }

}