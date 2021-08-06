package com.example.empresas

import android.app.Application
import com.example.di.*
import com.example.di.intent.intentModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmpresasApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            modules(
                intentModule +
                listOf(
                    presentationModule,
                    dataModule,
                    dataLocalModule,
                    dataRemoteModule,
                    domainModule
                )
            ).androidContext(applicationContext)
        }
    }
}