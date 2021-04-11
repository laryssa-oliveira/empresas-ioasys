package com.example.empresas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.empresas.remote.CompanyService
import com.example.empresas.remote.LoginRequest
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var button: AppCompatButton
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var viewLoading: View
    private lateinit var progressBar: ProgressBar

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
        Log.i("LoginActivity", "OnCreate")


        button.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                val response = CompanyService.newInstance().login(LoginRequest(
                        email = edtEmail.text.toString(),
                        password = edtPassword.text.toString()
                ))
                handleLogin(response)
            }
            viewLoading.visibility = View.VISIBLE
            progressBar.visibility = ProgressBar.VISIBLE
        }
    }

    private fun handleLogin(response: Response<Unit>) {
        if(response.isSuccessful){
            Log.d("LOGIN", "header access-token ${response.headers().get("access-token")}")
            Log.d("LOGIN", "header client ${response.headers().get("client")}")
            Log.d("LOGIN", "header ui ${response.headers().get("uid")}")

            findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToMainFragment(
                            accessToken = response.headers().get("access-token") ?: "",
                            clientId = response.headers().get("client") ?: "",
                            uid = response.headers().get("uid") ?: ""
                    )

            )
        }

    }
}