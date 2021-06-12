package id.adisuryantoro.footballclub.remote.recyclerviewadapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.adisuryantoro.footballclub.databinding.ItemClubsBinding
import id.adisuryantoro.footballclub.model.database.ClubDatabase

class ClubRecyclerViewAdapter(
    internal var context: Context,
    private var club: List<ClubObject>,
    private var teamListener: ClubListener
) : RecyclerView.Adapter<ClubRecyclerViewAdapter.MainViewHolder>() {

    // this method for build view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemClubsBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)

    }

    // this method for init item in every view item
    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val mClubDatabase = club[position].clubDatabase

        Glide
            .with(holder.itemView.context)
            .load(mClubDatabase.strTeamBadge)
            .centerCrop()
            .override(100, 100)
            .into(holder.binding.ivClubLogo)

        holder.binding.tvClubName.text = mClubDatabase.strTeam

        holder.itemView.setOnClickListener {
            teamListener.clubDetail(mClubDatabase)

        }

    }

    // this method for get total item
    override fun getItemCount(): Int {
        var itemCount = 0

        try {
            val itemSize = club.size
            itemCount = itemSize
        } catch (e: Exception) {
        }

        return itemCount
    }

    // this interface for handle more button pressed
    interface ClubListener {
        fun clubDetail(clubDatabase: ClubDatabase)

    }

    // this class is object of item in recyclerview
    class ClubObject(var clubDatabase: ClubDatabase)

    class MainViewHolder(val binding: ItemClubsBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
