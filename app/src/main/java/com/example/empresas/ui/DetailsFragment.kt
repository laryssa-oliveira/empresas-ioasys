package com.example.empresas.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.empresas.R
import com.example.empresas.databinding.FragmentDetailsBinding
import com.example.empresas.extensions.shareCompany
import com.example.empresas.presentation.DetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
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
            if (args.company.favorite) {
                binding.favCompany.setImageResource(R.drawable.ic_not_favorite)
            } else {
                binding.favCompany.setImageResource(R.drawable.ic_favorite)
            }
            detailsViewModel.favorite(args.company.favorite, args.company)
        }

        binding.share.setOnClickListener {
            shareCompany(args.company.description, args.company.pathImage!!)
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
        binding.companyName.text = args.company.companyName
        binding.descriptionCompany.text = args.company.description
        binding.descriptionCompany.movementMethod = ScrollingMovementMethod()
        if (args.company.favorite) {
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
            .placeholder(R.drawable.ic_logo)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.imageViewDetails)
    }

}