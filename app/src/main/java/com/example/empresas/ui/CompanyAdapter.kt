package com.example.empresas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.empresas.R
import com.example.empresas.domain.entities.Company


class CompanyAdapter(
    private val callback: (Company) -> Unit,
    private val callbackLike: (company: Company, like: Boolean) -> Unit
) : RecyclerView.Adapter<CompanyAdapter.CompaniesViewHolder>() {

    private var companies: List<Company> = emptyList()

    inner class CompaniesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(company: Company) {
            itemView.findViewById<AppCompatTextView>(R.id.textCompanyName).text =
                company.companyName
            itemView.findViewById<AppCompatTextView>(R.id.textCompanyRole).text =
                company.companyType?.companyTypeName
            itemView.findViewById<AppCompatTextView>(R.id.textCompanyCountry).text =
                company.country

            val imageView = itemView.findViewById<AppCompatImageView>(R.id.imageCompany)
            itemView.setOnClickListener { callback.invoke(company) }

            val favImageView = itemView.findViewById<AppCompatImageView>(R.id.favoriteCompany)
            if(company.favorite){
                favImageView.setImageResource(R.drawable.ic_favorite)
            }
            else{
                favImageView.setImageResource(R.drawable.ic_not_favorite)
            }

            favImageView.setOnClickListener {
                callbackLike.invoke(company, company.favorite)
                if(company.favorite){
                    company.favorite = false
                    favImageView.setImageResource(R.drawable.ic_not_favorite)
                }
                else{
                    company.favorite = true
                    favImageView.setImageResource(R.drawable.ic_favorite)
                }

            }

            Glide
                .with(itemView)
                .load(company.pathImage)
                .placeholder(R.drawable.ic_logo)
                .into(imageView);
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompaniesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_company, parent, false)
        return CompaniesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompaniesViewHolder, position: Int) {
        holder.bind(companies[position])
    }

    override fun getItemCount(): Int {
        return companies.size
    }

    fun setItems(list: List<Company>) {
        companies = list
    }
}

