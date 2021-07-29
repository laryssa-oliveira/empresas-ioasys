package com.example.empresas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class FavoriteCompaniesFragment : Fragment() {

    private lateinit var adapter: CompanyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var cardView: CardView
    private val favCompaniesViewModel by viewModel<FavoriteCompaniesViewModel>()
    private lateinit var loadingGroup: Group

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
        favCompaniesViewModel.getFavoriteCompanies()

        setObservers()
    }

    private fun setObservers() {
        favCompaniesViewModel.companyListLiveData.observe(viewLifecycleOwner, {
            when (it.state) {

                ViewState.State.SUCCESS -> onSuccess(it.data ?: listOf<Company>())
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
        } else {
            recyclerView.visibility = View.GONE
            cardView.visibility = View.VISIBLE
        }
    }

}