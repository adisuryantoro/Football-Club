package id.adisuryantoro.footballclub.remote

import androidx.lifecycle.LiveData
import id.adisuryantoro.footballclub.model.database.ClubDatabase

class DatabaseHandler(private val clubDao: ClubDao) {

    fun createTeam(clubDatabase: ClubDatabase) {
        clubDao.createClub(clubDatabase)

    }

    fun readClub(idClub: String): List<ClubDatabase> {
        return clubDao.readClub(idClub)

    }

    fun readFavoriteTeam(): LiveData<List<ClubDatabase>> {
        return clubDao.readFavoriteClub()

    }


    fun updateTeam(clubDatabase: ClubDatabase) {
        clubDao.updateClub(clubDatabase)

    }

    fun deleteTeam(clubDatabase: ClubDatabase) {
        clubDao.deleteClub(clubDatabase)

    }
}