package id.adisuryantoro.footballclub.remote

import androidx.lifecycle.LiveData
import androidx.room.*
import id.adisuryantoro.footballclub.model.database.ClubDatabase

@Dao
interface ClubDao {
    // Team ----------------------------------------------------------------------------------------
    @Insert
    fun createClub(clubDatabase: ClubDatabase)

    @Query("SELECT * FROM clubDatabase WHERE isFavorite = 1")
    fun readFavoriteClub(): LiveData<List<ClubDatabase>>

    @Query("SELECT * FROM clubDatabase WHERE idTeam = :idTeam")
    fun readClub(idTeam: String): List<ClubDatabase>

    @Update
    fun updateClub(clubDatabase: ClubDatabase)

    @Delete
    fun deleteClub(clubDatabase: ClubDatabase)

}