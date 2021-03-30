package com.example.empresas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { CompanyAdapter(::clickItem)}
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        recyclerView = findViewById(R.id.recyclerView)
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

    }
}