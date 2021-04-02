package com.example.empresas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.empresas.R

class LoginActivity : Fragment() {

    lateinit var button: AppCompatButton
    lateinit var viewLoading: View
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.btnLogin)
        viewLoading = view.findViewById(R.id.viewLoading)
        progressBar = view.findViewById(R.id.progressBar)
        Log.i("LoginActivity", "OnCreate")


        button.setOnClickListener{
            //val intent = Intent(this, MainFragment::class.java)
            //startActivity(intent)
            //viewLoading.visibility = View.VISIBLE
            //progressBar.visibility = ProgressBar.VISIBLE
        }
    }
}