package com.example.empresas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.empresas.presentation.ViewState.State.*

import com.example.empresas.R
import com.example.empresas.injection.Injection
import com.example.empresas.presentation.LoginViewModel
import com.example.empresas.presentation.LoginViewModelFactory

import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment() {

    private lateinit var button: AppCompatButton
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var viewLoading: View
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: LoginViewModel
    private lateinit var loadingGroup: Group

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.btnLogin)
        edtEmail = view.findViewById(R.id.edtEmail)
        edtPassword = view.findViewById(R.id.edtPassword)
        viewLoading = view.findViewById(R.id.viewLoading)
        progressBar = view.findViewById(R.id.progressBar)
        viewModel = Injection.provideLoginViewModel(this, requireContext())
        loadingGroup = view.findViewById(R.id.loadingGroup)

        button.setOnClickListener{
            viewModel.login(edtEmail.text.toString(), edtPassword.text.toString())
            viewLoading.visibility = View.VISIBLE
            progressBar.visibility = ProgressBar.VISIBLE
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.headersLiveData.observe(viewLifecycleOwner, {
            when(it.state) {

                SUCCESS -> onResultSuccess()
                ERROR -> onResultError(it.error)
                LOADING -> onLoading(it.isLoading)
            }

        })

    }

    private fun onLoading(loading: Boolean) {
        if(loading)
            loadingGroup.visibility = View.VISIBLE
         else
            loadingGroup.visibility = View.GONE

    }

    private fun onResultError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message?: "", Toast.LENGTH_LONG).show()

    }

    private fun onResultSuccess() {
            findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToMainFragment(                    )
            )
            viewModel.clearStatus()


    }

}