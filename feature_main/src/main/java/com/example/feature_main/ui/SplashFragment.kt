package com.example.feature_main.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.base_feature.ViewState
import com.example.base_feature.navDirections
import com.example.feature_main.databinding.FragmentSplashBinding
import com.example.feature_main.navigation.SplashNavigation
import com.example.feature_main.presentation.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModel()
    private lateinit var binding: FragmentSplashBinding
    private val navigation: SplashNavigation by navDirections()

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
            navigation.navigateToHome()
        }
        else
            navigation.navigateToLogin()
    }

}