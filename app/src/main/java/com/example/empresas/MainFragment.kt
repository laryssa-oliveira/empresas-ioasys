package com.example.empresas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {

    private val adapter by lazy { CompanyAdapter(::clickItem)}
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
                //.setSupportActionBar(toolbar)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter

        adapter.setItem(listOf(
                Company(
                    id = 1,
                    companyName = "Empresa 1",
                    description = "Exemplo teste",
                    pathImage = "https://thispersondoesnotexist.com/image",
                    country = "Brasil",
                    CompanyType (
                            id = 0,
                            companyTypeName = "valor"
                    )
                ),
                Company(
                        id = 1,
                        companyName = "Empresa 2",
                        description = "Exemplo teste",
                        pathImage = "https://thispersondoesnotexist.com/image",
                        country = "Brasil",
                        CompanyType (
                                id = 0,
                                companyTypeName = "valor"
                        )
                ),
                Company(
                        id = 1,
                        companyName = "Empresa 3",
                        description = "Exemplo teste",
                        pathImage = "https://thispersondoesnotexist.com/image",
                        country = "Brasil",
                        CompanyType (
                                id = 0,
                                companyTypeName = "valor"
                        )
                )
            )
        )
    }

    private fun clickItem(company: Company){
   //         val intent = Intent(this, DetailsActivity::class.java).apply {
   //             putExtra(DetailsActivity.EXTRA_NAME, company.companyName)
   //             putExtra(DetailsActivity.EXTRA_IMAGE, company.pathImage)
   //             putExtra(DetailsActivity.EXTRA_DESCRIPTION, company.description)
   //         }
   //         startActivity(intent)
    }
}