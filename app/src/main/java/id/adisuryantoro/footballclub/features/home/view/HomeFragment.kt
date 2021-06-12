package id.adisuryantoro.footballclub.features.home.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import id.adisuryantoro.footballclub.R
import id.adisuryantoro.footballclub.databinding.HomeFragmentBinding
import id.adisuryantoro.footballclub.features.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    lateinit var binding: HomeFragmentBinding

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding = HomeFragmentBinding.inflate(inflater,container, false)
        binding.contentAppbarToolbar.tvTitleFragment.setText(R.string.string_title_fragment_home)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}