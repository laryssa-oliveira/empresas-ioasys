package com.example.feature_main.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.base_feature.ViewState
import com.example.base_feature.navDirections
import com.example.domain.entities.Company
import com.example.feature_main.databinding.FragmentMainBinding
import com.example.feature_main.navigation.HomeNavigation
import com.example.feature_main.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var adapter: CompanyAdapter
    private val mainViewModel by viewModel<MainViewModel>()
    private lateinit var binding: FragmentMainBinding
    private val navigation: HomeNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getCompanies()

        setObservers()

        binding.searchMain.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mainViewModel.filter(s.toString())
            }
        })
    }

    private fun setObservers() {
        mainViewModel.companyListLiveData.observe(viewLifecycleOwner, {
            when (it.state) {

                ViewState.State.SUCCESS -> onSuccess(it.data ?: listOf<Company>())
                ViewState.State.ERROR -> onResultError(it.error)
                ViewState.State.LOADING -> onLoading(it.isLoading)
            }
        })

        mainViewModel.filterCompany.observe(viewLifecycleOwner, {
            when (it.state) {

                ViewState.State.SUCCESS -> onSuccess(it.data ?: listOf<Company>())
                ViewState.State.ERROR -> onResultError(it.error)
                ViewState.State.LOADING -> onLoading(it.isLoading)
            }
        })
    }

    private fun onLoading(loading: Boolean) {
        if (loading)
            binding.loadingGroupMain.visibility = View.VISIBLE
        else
            binding.loadingGroupMain.visibility = View.GONE

    }

    private fun onResultError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_LONG).show()
    }

    private fun onSuccess(list: List<Company>) {
        onLoading(false)
        adapter = CompanyAdapter(callback = ::clickItem, callbackLike = ::clickLikeItem)
        adapter.setItems(list)
        binding.recyclerView.adapter = adapter
    }

    private fun clickItem(company: Company) {
        navigation.navigateToDetails(company)
    }

    private fun clickLikeItem(company: Company, like: Boolean) {
        mainViewModel.favorite(like, company)
    }
}