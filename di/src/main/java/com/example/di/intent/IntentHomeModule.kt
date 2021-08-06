package com.example.di.intent

import androidx.fragment.app.Fragment
import com.example.feature_main.navigation.DetailsNavigation
import com.example.feature_main.navigation.HomeNavigation
import com.example.intent.DetailsNavigationImpl
import com.example.intent.HomeNavigationImpl
import org.koin.dsl.module

val intentHomeModule = module {
    factory<HomeNavigation> { (fragment: Fragment) ->
        HomeNavigationImpl(fragment)
    }

    factory<DetailsNavigation> { (fragment: Fragment) ->
        DetailsNavigationImpl(fragment)
    }
}