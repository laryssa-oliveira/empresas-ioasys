package com.example.empresas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.empresas.Company
import com.example.empresas.R
import com.example.empresas.injection.Injection
import com.example.empresas.presentation.MainViewModel
import com.example.empresas.presentation.MainViewModelFactory
import com.example.empresas.presentation.ViewState


class MainFragment : Fragment() {

    private val adapter by lazy { CompanyAdapter(::clickItem) }
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        viewModel = Injection.provideMainViewModel(this, requireContext())
        viewModel.getCompanies()

        setupToolbar()
        setObservers()

    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setObservers() {
        viewModel.companyListLiveData.observe(viewLifecycleOwner, {
            when(it.state) {

                ViewState.State.SUCCESS -> onSuccess(it.data ?: listOf<Company>())
                ViewState.State.ERROR -> onResultError(it.error)
                ViewState.State.LOADING -> onLoading(it.isLoading)
            }
        })
    }

    private fun onLoading(loading: Boolean) {
        TODO("Not yet implemented")

    }

    private fun onResultError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message?: "", Toast.LENGTH_LONG).show()
    }

    private fun onSuccess(list: List<Company>){
        adapter.setItems(list)

    }


    private fun clickItem(company: Company){
        findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailsFragment(
                        name = company.companyName,
                        imageUrl = company.pathImage,
                        description = company.description
                )
        )
    }
}