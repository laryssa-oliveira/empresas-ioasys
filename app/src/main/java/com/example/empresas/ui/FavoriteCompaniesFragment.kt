package com.example.empresas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.Group
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.empresas.R
import com.example.empresas.domain.entities.Company
import com.example.empresas.presentation.FavoriteCompaniesViewModel
import com.example.empresas.presentation.MainViewModel
import com.example.empresas.presentation.ViewState
import org.koin.android.viewmodel.ext.android.viewModel
import android.text.Editable

import android.text.TextWatcher




class FavoriteCompaniesFragment : Fragment() {

    private lateinit var adapter: CompanyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var cardView: CardView
    private val favCompaniesViewModel by viewModel<FavoriteCompaniesViewModel>()
    private lateinit var loadingGroup: Group
    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_companies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = requireActivity().findViewById(R.id.recyclerViewFavorite)
        cardView = requireActivity().findViewById(R.id.cardNoFavorite)
        loadingGroup = requireActivity().findViewById(R.id.loadingGroupFavorite)
        searchEditText = requireActivity().findViewById(R.id.searchFavorite)
        favCompaniesViewModel.getFavoriteCompanies()

        setObservers()

        searchEditText.addTextChangedListener(object : TextWatcher {
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
        adapter = CompanyAdapter(callback = ::clickItem, callbackLike = ::clickLikeItem)
        adapter.setItems(list)
        recyclerView.adapter = adapter
        updateUI(list)
    }

    private fun onSuccessFilter(list: List<Company>) {
        onLoading(false)
        adapter = CompanyAdapter(callback = ::clickItem, callbackLike = ::clickLikeItem)
        adapter.setItems(list)
        recyclerView.adapter = adapter
    }

    private fun clickItem(company: Company) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(company)
        )

    }

    private fun clickLikeItem(company: Company, like: Boolean) {
        favCompaniesViewModel.favoriteCompanies(like, company)
    }



    private fun onLoading(loading: Boolean) {
        if (loading)
            loadingGroup.visibility = View.VISIBLE
        else
            loadingGroup.visibility = View.GONE

    }

    private fun onResultError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_LONG).show()
    }


    private fun updateUI(list: List<Company>) {

        if (list.isNotEmpty()) {
            recyclerView.visibility = View.VISIBLE
            cardView.visibility = View.GONE
            searchEditText.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.GONE
            cardView.visibility = View.VISIBLE
            searchEditText.visibility = View.GONE
        }
    }

}