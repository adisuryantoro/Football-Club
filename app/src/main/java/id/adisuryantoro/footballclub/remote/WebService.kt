package id.adisuryantoro.footballclub.remote

import android.os.Build
import androidx.viewbinding.BuildConfig
import id.adisuryantoro.footballclub.model.Club
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("api/v1/json/1/search_all_teams.php")
    fun readTeamList(@Query("l") leagueName: String): Call<Club>

    @GET("api/v1/json/1/searchteams.php")
    fun readSearchTeam(@Query("t") keyword: String): Call<Club>

    @GET("api/v1/json/1/lookupteam.php")
    fun readTeamDetail(@Query("id") id: String): Call<Club>

    companion object Factory {
        fun create(): WebService {
            val retrofit = Retrofit.Builder()
                .baseUrl(id.adisuryantoro.footballclub.BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WebService::class.java)
        }
    }

}