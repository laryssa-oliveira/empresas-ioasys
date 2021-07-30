package com.example.empresas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.empresas.databinding.FragmentLoginBinding
import com.example.empresas.presentation.LoginViewModel
import com.example.empresas.presentation.ViewState.State.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()
    private lateinit var binding: FragmentLoginBinding

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

                SUCCESS -> onResultSuccess()
                ERROR -> onResultError(it.error)
                LOADING -> onLoading(it.isLoading)
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
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        )


    }

}