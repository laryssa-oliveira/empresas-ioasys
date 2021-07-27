package com.example.empresas

import android.app.Application
import com.example.empresas.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmpresasApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@EmpresasApplication)
            modules(listOf(
                    presentationModule,
                    dataModule,
                    dataLocalModule,
                    dataRemoteModule,
                    domainModule
            )
        )
        }
    }
}