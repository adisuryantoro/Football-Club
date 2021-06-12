package id.adisuryantoro.footballclub.features.favorite.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import id.adisuryantoro.footballclub.R
import id.adisuryantoro.footballclub.databinding.FavoritesFragmentBinding
import id.adisuryantoro.footballclub.features.favorite.viewmodel.FavoritesViewModel

class FavoritesFragment : Fragment() {

    lateinit var binding: FavoritesFragmentBinding

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        binding.contentAppbarToolbar.tvTitleFragment.setText(R.string.string_title_fragment_favorites)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}