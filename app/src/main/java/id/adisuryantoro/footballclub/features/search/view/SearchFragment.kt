package id.adisuryantoro.footballclub.features.search.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import id.adisuryantoro.footballclub.R
import id.adisuryantoro.footballclub.databinding.SearchFragmentBinding
import id.adisuryantoro.footballclub.features.search.viewmodel.SearchViewModel

class SearchFragment : Fragment() {
    lateinit var binding: SearchFragmentBinding

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding = SearchFragmentBinding.inflate(inflater, container,false)
        binding.contentAppbarToolbar.tvTitleFragment.setText(R.string.string_title_fragment_search)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}