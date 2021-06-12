package id.adisuryantoro.footballclub.remote

import id.adisuryantoro.footballclub.model.Club
import id.adisuryantoro.footballclub.model.League
import id.adisuryantoro.footballclub.remote.retrofit.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClubRepository(private val webService: WebService, private val clubDao: ClubDao) {
    val databaseHandler = DatabaseHandler(clubDao)

    fun requestLeagueList(responseListener: ResponseListener) {
        webService.requestLeague().enqueue(object : Callback<League> {
            override fun onFailure(call: Call<League>, t: Throwable) {
                ResponseHelper.errorResponseHandler(
                    responseListener,
                    t,
                    "onFailure, requestLeagueList, UserRepository"
                )

            }

            override fun onResponse(call: Call<League>, response: Response<League>) {
                ResponseHelper.responseHandlerOK(response, responseListener)

            }

        })

    }

    fun requestClubList(responseListener: ResponseListener, leagueName: String) {
        webService.readClubList(leagueName).enqueue(object : Callback<Club> {
            override fun onFailure(call: Call<Club>, t: Throwable) {
                ResponseHelper.errorResponseHandler(
                    responseListener,
                    t,
                    "onFailure, requestTeamList, UserRepository"
                )
            }

            override fun onResponse(
                call: Call<Club>,
                response: Response<Club>
            ) {
                ResponseHelper.responseHandlerOK(response, responseListener)
            }
        })

    }
}