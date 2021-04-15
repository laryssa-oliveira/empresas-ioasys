package com.example.empresas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.empresas.R
import com.example.empresas.presentation.LoginViewModel
import com.example.empresas.presentation.ViewModelFactory
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    private lateinit var button: AppCompatButton
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var viewLoading: View
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: LoginViewModel

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
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(LoginViewModel::class.java)

        button.setOnClickListener{
            viewModel.login(edtEmail.text.toString(), edtPassword.text.toString())
            viewLoading.visibility = View.VISIBLE
            progressBar.visibility = ProgressBar.VISIBLE
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.headersLiveData.observe(viewLifecycleOwner, {
            it?.let { headers ->
                findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToMainFragment(
                                accessToken = headers["access-token"] ?: "",
                                clientId = headers["client"] ?: "",
                                uid = headers["uid"] ?: ""
                        )
                )
                viewModel.clearStatus()
            }

        })

    }

}