package com.example.empresas.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.empresas.R
import com.example.empresas.databinding.FragmentMainBinding
import com.example.empresas.databinding.FragmentSplashBinding
import com.example.empresas.domain.entities.Company
import com.example.empresas.presentation.SplashViewModel
import com.example.empresas.presentation.ViewState
import org.koin.android.viewmodel.ext.android.viewModel


class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModel()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Handler(requireActivity().mainLooper).postDelayed({
            viewModel.isLogged()
        }, 2500)
        setObservers()
    }

    private fun setObservers() {
        viewModel.isLoggedViewState.observe(viewLifecycleOwner, {
            when(it.state) {

                ViewState.State.SUCCESS -> onSuccess(it.data ?: false)
                ViewState.State.ERROR -> onResultError(it.error)
            }
        })
    }

    private fun onResultError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message?: "", Toast.LENGTH_LONG).show()
    }

    private fun onSuccess(data: Boolean){
        if (data){
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }
        else
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())

    }

}