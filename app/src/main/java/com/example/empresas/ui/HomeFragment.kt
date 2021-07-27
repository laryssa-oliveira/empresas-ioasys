package com.example.empresas.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.empresas.R
import com.example.empresas.presentation.HomeViewModel
import com.example.empresas.presentation.ViewState
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var dialogFragment: DialogFragment
    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var loadingGroup: Group

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = requireActivity().findViewById(R.id.tabLayout)
        viewPager = requireActivity().findViewById(R.id.viewPager)
        toolbar = requireActivity().findViewById(R.id.mainToolbar)
        loadingGroup = requireActivity().findViewById(R.id.loadingGroupHome)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

        pagerAdapter = PageAdapter(childFragmentManager, tabLayout.tabCount)
        viewPager.adapter = pagerAdapter
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                pagerAdapter.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

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
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
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
            loadingGroup.visibility = View.VISIBLE
        else
            loadingGroup.visibility = View.GONE

    }

    private fun onResultError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message ?: "", Toast.LENGTH_LONG).show()
    }


}
