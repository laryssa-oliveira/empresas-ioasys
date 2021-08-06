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
import com.example.feature_main.databinding.FragmentFavoriteCompaniesBinding
import com.example.feature_main.navigation.HomeNavigation
import com.example.feature_main.presentation.FavoriteCompaniesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteCompaniesFragment : Fragment() {

    private lateinit var adapter: FavoriteCompanyAdapter
    private val favCompaniesViewModel by viewModel<FavoriteCompaniesViewModel>()
    private lateinit var binding: FragmentFavoriteCompaniesBinding
    private val navigation: HomeNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteCompaniesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favCompaniesViewModel.getFavoriteCompanies()

        setObservers()

        binding.searchFavorite.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                favCompaniesViewModel.filter(s.toString())
            }
        })
    }

    private fun setObservers() {
        favCompaniesViewModel.companyListLiveData.observe(viewLifecycleOwner, {
            when (it.state) {

                ViewState.State.SUCCESS -> onSuccess(it.data ?: listOf<Company>())
                ViewState.State.ERROR -> onResultError(it.error)
                ViewState.State.LOADING -> onLoading(it.isLoading)
            }
        })

        favCompaniesViewModel.filterCompany.observe(viewLifecycleOwner, {
            when (it.state) {

                ViewState.State.SUCCESS -> onSuccessFilter(it.data ?: listOf<Company>())
                ViewState.State.ERROR -> onResultError(it.error)
                ViewState.State.LOADING -> onLoading(it.isLoading)
            }
        })

    }

    private fun onSuccess(list: List<Company>) {
        onLoading(false)
        adapter = FavoriteCompanyAdapter(callback = ::clickItem, callbackLike = ::clickLikeItem, callbackUpdateUi = {updateUI(it)})
        adapter.setItems(list.toMutableList())
        binding.recyclerViewFavorite.adapter = adapter
        updateUI(list)
    }

    private fun onSuccessFilter(list: List<Company>) {
        onLoading(false)
        adapter = FavoriteCompanyAdapter(callback = ::clickItem, callbackLike = ::clickLikeItem, callbackUpdateUi = {updateUI(it)})
        adapter.setItems(list.toMutableList())
        binding.recyclerViewFavorite.adapter = adapter
    }

    private fun clickItem(company: Company) {
        navigation.navigateToDetails(company)

    }

    private fun clickLikeItem(company: Company, like: Boolean) {
        favCompaniesViewModel.favoriteCompanies(like, company)
    }


    private fun onLoading(loading: Boolean) {
        if (loading)
            binding.loadingGroupFavorite.visibility = View.VISIBLE
        else
            binding.loadingGroupFavorite.visibility = View.GONE

    }

    private fun onResultError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_LONG).show()
    }


    private fun updateUI(list: List<Company>) {

        if (list.isNotEmpty()) {
            binding.recyclerViewFavorite.visibility = View.VISIBLE
            binding.cardNoFavorite.visibility = View.GONE
            binding.searchFavorite.visibility = View.VISIBLE
        } else {
            binding.recyclerViewFavorite.visibility = View.GONE
            binding.cardNoFavorite.visibility = View.VISIBLE
            binding.searchFavorite.visibility = View.GONE
        }
    }

}