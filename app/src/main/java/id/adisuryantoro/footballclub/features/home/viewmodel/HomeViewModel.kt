package id.adisuryantoro.footballclub.features.home.viewmodel

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import id.adisuryantoro.footballclub.R
import id.adisuryantoro.footballclub.model.ClubDetail
import id.adisuryantoro.footballclub.model.LeagueDetail
import id.adisuryantoro.footballclub.model.database.ClubDatabase
import id.adisuryantoro.footballclub.remote.ClubRepository
import id.adisuryantoro.footballclub.remote.ResponseListener
import id.adisuryantoro.footballclub.remote.recyclerviewadapter.ClubRecyclerViewAdapter

class HomeViewModel : ViewModel() {
    private lateinit var clubRepository: ClubRepository

    private val clubObjects: MutableList<ClubRecyclerViewAdapter.ClubObject> = ArrayList()

    private lateinit var clubObject: ClubRecyclerViewAdapter.ClubObject

    fun init(clubRepository: ClubRepository) {
        this.clubRepository = clubRepository

    }


    fun requestLeagueList(responseListener: ResponseListener) {
        clubRepository.requestLeagueList(responseListener)

    }

    fun requestTeamList(responseListener: ResponseListener, leagueName: String) {
        clubRepository.requestClubList(responseListener, leagueName)

    }

    fun initClubList(clubList: List<ClubDetail>?) {
        clubObjects.clear()

        if (clubList != null) {
            for (i in clubList.indices) {
                clubObject = ClubRecyclerViewAdapter.ClubObject(getTeamObject(clubList[i]))
                clubObjects.add(clubObject)

            }

        }

    }

    fun getTeamObjects(): MutableList<ClubRecyclerViewAdapter.ClubObject> {
        return clubObjects
    }

    fun addSpinner(it: List<LeagueDetail>?, context: Context): ArrayAdapter<String> {
        val spinnerNameList = ArrayList<String>()

        if (it != null) {
            for (i in it.indices) {
                val a1 = it[i].strLeague
                if (a1 != null) {
                    spinnerNameList.add(a1)

                }

            }

        }

        val aa = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, spinnerNameList)
        aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

        return aa

    }

    private fun getTeamObject(clubDetail: ClubDetail): ClubDatabase {
        return ClubDatabase(
            0,
            clubDetail.idTeam!!,
            null,
            clubDetail.strTeamBadge,
            clubDetail.strTeam,
            clubDetail.intFormedYear,
            clubDetail.strStadium,
            clubDetail.strDescriptionEN
        )

    }
}