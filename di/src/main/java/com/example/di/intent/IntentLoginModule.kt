package com.example.di.intent

import androidx.fragment.app.Fragment
import com.example.feature_login.navigation.LoginNavigation
import com.example.intent.LoginNavigationImpl
import org.koin.dsl.module

val intentLoginModule = module {
    factory<LoginNavigation> { (fragment: Fragment) ->
        LoginNavigationImpl(fragment)
    }
}