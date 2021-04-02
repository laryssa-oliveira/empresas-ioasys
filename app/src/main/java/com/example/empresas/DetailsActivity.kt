package com.example.empresas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class DetailsActivity: AppCompatActivity (){

    private lateinit var toolbarDetailsActivity: Toolbar
    private lateinit var imageViewDetails: AppCompatImageView
    private lateinit var companyName: AppCompatTextView
    private lateinit var descriptionCompany: AppCompatTextView

    companion object{
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_IMAGE = "EXTRA_IMAGE"
        const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
    }

    private val name by lazy{
        intent.getStringExtra(EXTRA_NAME)
    }

    private val imageUrl by lazy{
        intent.getStringExtra(EXTRA_IMAGE)
    }

    private val description by lazy{
        intent.getStringExtra(EXTRA_DESCRIPTION)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        toolbarDetailsActivity = findViewById(R.id.toolbarDetails)
        imageViewDetails = findViewById(R.id.imageViewDetails)
        companyName = findViewById(R.id.companyName)
        descriptionCompany = findViewById(R.id.descriptionCompany)
        //setupToolbar()
        //configureView()
    }

    private fun setImageContent(){
        Glide
                .with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_logo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageViewDetails)
    }

}