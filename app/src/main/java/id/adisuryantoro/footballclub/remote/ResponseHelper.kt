package id.adisuryantoro.footballclub.remote

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.adisuryantoro.footballclub.model.ClubDetail
import id.adisuryantoro.footballclub.model.database.ClubDatabase
import id.adisuryantoro.footballclub.remote.retrofit.WebService
import retrofit2.Response

object ResponseHelper {
    private var clubRepository: ClubRepository? = null

    private var leagueIdHolder = MutableLiveData<String>()

    private val hasFragmentBackstack = mutableMapOf<String, Boolean>()

    private val searchClubList = MutableLiveData<List<ClubDetail>>()

    private lateinit var selectedClub: ClubDatabase

    private var isFromAPI = true

    fun init(context: Context) {
        if (clubRepository == null) {
            clubRepository =
                ClubRepository(WebService.create(), ClubRoomDatabase.getInstance(context).clubDao())

        }

    }

    fun getUserRepository(): ClubRepository {
        return clubRepository!!
    }

    fun setLeagueIdHolder(idHolder: String) {
        leagueIdHolder.value = idHolder

    }

    fun getLeagueIdHolderListener(): LiveData<String> {
        return leagueIdHolder
    }

    fun getLeagueIdHolder(): String? {
        return leagueIdHolder.value
    }

    fun setSearchTeamList(searchTeamList: List<ClubDetail>?) {
        this.searchClubList.value = searchTeamList

    }

    fun getSearchTeamList(): LiveData<List<ClubDetail>>? {
        return searchClubList
    }

    fun setHasFragmentBackstack(fragmentName: String, state: Boolean) {
        hasFragmentBackstack[fragmentName] = state
    }

    fun getHasFragmentBackstack(fragmentName: String): Boolean {
        return hasFragmentBackstack[fragmentName] ?: false
    }

    fun setSelectedTeam(selectedTeam: ClubDatabase) {
        this.selectedClub = selectedTeam

    }

    fun getSelectedTeam(): ClubDatabase {
        return selectedClub
    }

    fun setIsFromAPI(isFromAPI: Boolean) {
        this.isFromAPI = isFromAPI
    }

    fun getIsFromAPI(): Boolean {
        return isFromAPI
    }

    fun errorResponseHandler(
        responseListener: ResponseListener,
        t: Throwable,
        description: String
    ) {
        var message = description
        val mMessage = t.message
        val mCause = t.cause?.message

        if ((mMessage != null) && (mCause != null)) {
            message = "$mMessage # $mCause"
        } else {
            if (mMessage != null) {
                message = mMessage
            }

            if (mCause != null) {
                message = mCause
            }
        }

        responseListener.retrofitResponse(
            RetrofitResponse(
                false,
                message,
                null
            )
        )

    }

    fun responseHandlerOK(retrofitResponse: Any, responseListener: ResponseListener) {
        val response = retrofitResponse as Response<*>
        var isSuccess = false
        val message: String
        val rb = response.body()

        if (rb != null) {
            isSuccess = true
            message = "Request response is OK"

        } else {
            message = "Response body is null"
        }

        responseListener.retrofitResponse(
            RetrofitResponse(
                isSuccess,
                message,
                rb
            )
        )

    }

}