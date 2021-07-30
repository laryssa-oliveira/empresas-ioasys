package com.example.empresas.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.example.empresas.R
import com.example.empresas.extensions.shareCompany
import com.example.empresas.presentation.DetailsViewModel
import com.example.empresas.presentation.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment(){

    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var toolbar: Toolbar
    private lateinit var imageViewDetails: AppCompatImageView
    private lateinit var companyName: AppCompatTextView
    private lateinit var descriptionCompany: AppCompatTextView
    private lateinit var favoriteCompany: AppCompatImageView
    private lateinit var share: AppCompatImageView
    private val detailsViewModel by viewModel<DetailsViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        imageViewDetails = view.findViewById(R.id.imageViewDetails)
        companyName = view.findViewById(R.id.companyName)
        descriptionCompany = view.findViewById(R.id.descriptionCompany)
        favoriteCompany = view.findViewById(R.id.favCompany)
        share = view.findViewById(R.id.share)

        favoriteCompany.setOnClickListener {
            if (args.company.favorite){
                favoriteCompany.setImageResource(R.drawable.ic_not_favorite)
            }
            else{
                favoriteCompany.setImageResource(R.drawable.ic_favorite)
            }
            detailsViewModel.favorite(args.company.favorite, args.company)
        }

        share.setOnClickListener {
            shareCompany(args.company.description, args.company.pathImage!!)
        }

        setupToolbar()
        configureView()

    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun configureView() {
        companyName.text = args.company.companyName
        descriptionCompany.text = args.company.description
        descriptionCompany.movementMethod = ScrollingMovementMethod()
        if (args.company.favorite){
            favoriteCompany.setImageResource(R.drawable.ic_favorite)
        }
        else {
            favoriteCompany.setImageResource(R.drawable.ic_not_favorite)
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
                .into(imageViewDetails)
    }

}