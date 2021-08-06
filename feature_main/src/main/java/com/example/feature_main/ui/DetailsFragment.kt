package com.example.feature_main.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.base_feature.navDirections
import com.example.empresas.extensions.shareCompany
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentDetailsBinding
import com.example.feature_main.navigation.DetailsNavigation
import com.example.feature_main.presentation.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private val navigation: DetailsNavigation by navDirections()
    private val detailsViewModel by viewModel<DetailsViewModel>()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favCompany.setOnClickListener {
            if (navigation.company.favorite) {
                binding.favCompany.setImageResource(R.drawable.ic_not_favorite)
            } else {
                binding.favCompany.setImageResource(R.drawable.ic_favorite)
            }
            detailsViewModel.favorite(navigation.company.favorite, navigation.company)
        }

        binding.share.setOnClickListener {
            shareCompany(navigation.company.description, navigation.company.pathImage!!)
        }

        setupToolbar()
        configureView()

    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun configureView() {
        binding.companyName.text = navigation.company.companyName
        binding.descriptionCompany.text = navigation.company.description
        binding.descriptionCompany.movementMethod = ScrollingMovementMethod()
        if (navigation.company.favorite) {
            binding.favCompany.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.favCompany.setImageResource(R.drawable.ic_not_favorite)
        }

        setImageContent()
    }

    private fun setImageContent() {
        Glide
            .with(this)
            .load("https://thispersondoesnotexist.com/image")
            .placeholder(R.drawable.logo_home)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.imageViewDetails)
    }

}