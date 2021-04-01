package com.example.empresas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import com.example.empresas.R

class LoginActivity : AppCompatActivity() {

    lateinit var button: AppCompatButton
    lateinit var viewLoading: View
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        button = findViewById(R.id.btnLogin)
        viewLoading = findViewById(R.id.viewLoading)
        progressBar = findViewById(R.id.progressBar)
        Log.i("LoginActivity", "OnCreate")


        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            viewLoading.visibility = View.VISIBLE
            progressBar.visibility = ProgressBar.VISIBLE
        }
    }
}