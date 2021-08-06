package com.example.di.intent

import androidx.fragment.app.Fragment
import com.example.feature_main.navigation.SplashNavigation
import com.example.intent.SplashNavigationImpl
import org.koin.dsl.module

val intentSplashModule = module {
    factory<SplashNavigation> { (fragment: Fragment) ->
        SplashNavigationImpl(fragment)
    }
}