package com.example.intent

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.feature_main.navigation.DetailsNavigation
import com.example.feature_main.ui.DetailsFragmentArgs

class DetailsNavigationImpl(fragment: Fragment): DetailsNavigation {
    private val args = fragment.navArgs<DetailsFragmentArgs>().value
    override val company = args.company
}