package com.example.feature_main.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.example.base_feature.ViewState
import com.example.base_feature.navDirections
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentHomeBinding
import com.example.feature_main.navigation.HomeNavigation
import com.example.feature_main.navigation.SplashNavigation
import com.example.feature_main.presentation.HomeViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var dialogFragment: DialogFragment
    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private val navigation: HomeNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.mainToolbar)

        pagerAdapter = PageAdapter(childFragmentManager, binding.tabLayout.tabCount)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
                pagerAdapter.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnLogout -> {
                dialogFragment = DialogFragment(
                    onYesEvent = { homeViewModel.logout() },
                    onNoEvent = { dialogFragment.dismiss() }
                )
                dialogFragment.show(childFragmentManager, "dialogFragment")
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun onSuccessLogout() {
        navigation.navigateToLogin()
    }

    private fun setObservers() {
        homeViewModel.logoutLiveData.observe(viewLifecycleOwner, {
            when (it.state) {

                ViewState.State.SUCCESS -> onSuccessLogout()
                ViewState.State.ERROR -> onResultError(it.error)
                ViewState.State.LOADING -> onLoading(it.isLoading)
            }
        })
    }

    private fun onLoading(loading: Boolean) {
        if (loading)
            binding.loadingGroupHome.visibility = View.VISIBLE
        else
            binding.loadingGroupHome.visibility = View.GONE

    }

    private fun onResultError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_LONG).show()
    }


}
