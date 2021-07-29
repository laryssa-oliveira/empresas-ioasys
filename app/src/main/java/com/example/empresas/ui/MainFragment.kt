package com.example.empresas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.empresas.R
import com.example.empresas.domain.entities.Company
import com.example.empresas.presentation.MainViewModel
import com.example.empresas.presentation.ViewState
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private lateinit var adapter: CompanyAdapter
    private lateinit var recyclerView: RecyclerView
    private val mainViewModel by viewModel<MainViewModel>()
    private lateinit var loadingGroup: Group

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = requireActivity().findViewById(R.id.recyclerView)
        loadingGroup = requireActivity().findViewById(R.id.loadingGroupMain)
        mainViewModel.getCompanies()

        setObservers()
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
            loadingGroup.visibility = View.VISIBLE
        else
            loadingGroup.visibility = View.GONE

    }

    private fun onResultError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_LONG).show()
    }

    private fun onSuccess(list: List<Company>) {
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
        mainViewModel.favorite(like, company)
    }
}