package com.example.feature_login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.base_feature.ViewState
import com.example.base_feature.navDirections
import com.example.feature_login.navigation.LoginNavigation
import com.example.feature_login.presentation.LoginViewModel
import feature_login.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()
    private lateinit var binding: FragmentLoginBinding
    private val navigation: LoginNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            loginViewModel.login(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString()
            )
        }
        setObservers()
        onLoading(false)

        (requireActivity() as? AppCompatActivity)?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        )
    }

    private fun setObservers() {
        loginViewModel.headersLiveData.observe(viewLifecycleOwner, {
            when (it.state) {

                ViewState.State.SUCCESS -> onResultSuccess()
                ViewState.State.ERROR -> onResultError(it.error)
                ViewState.State.LOADING -> onLoading(it.isLoading)
            }

        })

    }

    private fun onLoading(loading: Boolean) {
        if (loading)
            binding.loadingGroup.visibility = View.VISIBLE
        else
            binding.loadingGroup.visibility = View.GONE

    }

    private fun onResultError(error: Throwable?) {
        onLoading(false)
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_LONG).show()

    }

    private fun onResultSuccess() {
        onLoading(false)
        navigation.navigateToHome()
    }

}