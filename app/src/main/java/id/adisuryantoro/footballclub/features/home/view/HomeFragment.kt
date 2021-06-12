package id.adisuryantoro.footballclub.features.home.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.adisuryantoro.footballclub.MainActivity
import id.adisuryantoro.footballclub.R
import id.adisuryantoro.footballclub.databinding.HomeFragmentBinding
import id.adisuryantoro.footballclub.features.home.viewmodel.HomeViewModel
import id.adisuryantoro.footballclub.model.Club
import id.adisuryantoro.footballclub.model.League
import id.adisuryantoro.footballclub.model.database.ClubDatabase
import id.adisuryantoro.footballclub.remote.ResponseHelper
import id.adisuryantoro.footballclub.remote.ResponseListener
import id.adisuryantoro.footballclub.remote.RetrofitResponse
import id.adisuryantoro.footballclub.remote.recyclerviewadapter.ClubRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    lateinit var clubAdapter: ClubRecyclerViewAdapter

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

        lifecycleScope.launchWhenStarted {
            if (lifecycle.currentState >= Lifecycle.State.STARTED) {
                (activity as MainActivity).startLoading()

                withContext(Dispatchers.Default) {
                    viewModel.init(
                        ResponseHelper.getUserRepository()
                    )

                }
                initListener()
                initRecyclerView()

                withContext(Dispatchers.IO) {
                    viewModel.requestLeagueList(object :
                        ResponseListener {
                        override fun retrofitResponse(retrofitResponse: RetrofitResponse) {
                            responseAction(retrofitResponse)

                        }


                    })

                }

            }

        }

    }

    private fun responseAction(retrofitResponse: RetrofitResponse) {
        lifecycleScope.launchWhenStarted {
            if (lifecycle.currentState >= Lifecycle.State.STARTED) {
                withContext(Dispatchers.Default) {
                    if (retrofitResponse.isSuccess) {
                        val ab = retrofitResponse.responseBody as League
                        val aa = viewModel.addSpinner(
                            ab.leagues,
                            (activity as MainActivity)
                        )

                        withContext(Dispatchers.Main) { binding.contentAppbarToolbar.spinnerTeam.adapter = aa }

                    } else {
                        withContext(Dispatchers.Main) {
                            (activity as MainActivity).popUp(retrofitResponse.message)
                        }
                    }

                }

                (activity as MainActivity).stopLoading()

            }

        }
    }

    private fun initListener() {
        binding.contentAppbarToolbar.spinnerTeam.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    spinnerListener(parent!!.getItemAtPosition(position).toString())
                }


            }
    }

    private fun spinnerListener(leagueName: String) {
        lifecycleScope.launchWhenStarted {
            if (lifecycle.currentState >= Lifecycle.State.STARTED) {
                (activity as MainActivity).startLoading()

                withContext(Dispatchers.IO) {
                    viewModel.requestTeamList(object :
                        ResponseListener {
                        override fun retrofitResponse(retrofitResponse: RetrofitResponse) {
                            teamListHandler(retrofitResponse)

                        }


                    }, leagueName)

                }

            }

        }
    }

    private fun teamListHandler(retrofitResponse: RetrofitResponse) {
        lifecycleScope.launchWhenStarted {
            if (lifecycle.currentState >= Lifecycle.State.STARTED) {

                withContext(Dispatchers.Default) {
                    if (retrofitResponse.isSuccess) {
                        val LMEData =
                            retrofitResponse.responseBody as Club

                        viewModel.initClubList(LMEData.clubs)
                        withContext(Dispatchers.Main) { clubAdapter.notifyDataSetChanged() }

                    } else {
                        withContext(Dispatchers.Main) {
                            (activity as MainActivity)
                                .popUp(retrofitResponse.message)
                        }
                    }

                }

                (activity as MainActivity).stopLoading()

            }

        }
    }

    private fun initRecyclerView() {
        clubAdapter = ClubRecyclerViewAdapter(
            requireContext(),
            viewModel.getTeamObjects(),
            object : ClubRecyclerViewAdapter.ClubListener {
                override fun clubDetail(clubDatabase: ClubDatabase) {
                    selectedItemListener(clubDatabase)

                }


            })
        val mLayoutManager = LinearLayoutManager(context)

        binding.rvHomeTeam.layoutManager = mLayoutManager
        binding.rvHomeTeam.itemAnimator = DefaultItemAnimator()
        binding.rvHomeTeam.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.rvHomeTeam.adapter = clubAdapter
    }

    private fun selectedItemListener(clubDatabase: ClubDatabase) {
        lifecycleScope.launchWhenStarted {
            if (lifecycle.currentState >= Lifecycle.State.STARTED) {
                (activity as MainActivity).startLoading()

                withContext(Dispatchers.Default) {
                    ResponseHelper.setSelectedTeam(clubDatabase)
                    ResponseHelper.setIsFromAPI(true)
                }

                (activity as MainActivity).stopLoading()

            }

        }

    }

}