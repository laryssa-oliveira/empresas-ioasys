package com.example.empresas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment


class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).let {
            val navController = it.navController
            navController.setGraph(R.navigation.main_graph)
        }
        
    }

}