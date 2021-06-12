package id.adisuryantoro.footballclub.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ClubDatabase(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var idTeam: String?,
    var isFavorite: Boolean?,
    var strTeamBadge: String?,
    var strTeam: String?,
    var intFormedYear: String?,
    var strStadium: String?,
    var strDescriptionEN: String?
)