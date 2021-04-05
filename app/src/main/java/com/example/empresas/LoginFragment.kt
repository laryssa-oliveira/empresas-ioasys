package com.example.empresas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    lateinit var button: AppCompatButton
    lateinit var viewLoading: View
    lateinit var progressBar: ProgressBar

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
        viewLoading = view.findViewById(R.id.viewLoading)
        progressBar = view.findViewById(R.id.progressBar)
        Log.i("LoginActivity", "OnCreate")


        button.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
            viewLoading.visibility = View.VISIBLE
            progressBar.visibility = ProgressBar.VISIBLE
        }
    }
}