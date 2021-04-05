package com.example.empresas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {

    private val adapter by lazy { CompanyAdapter(::clickItem)}
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    lateinit var button: AppCompatButton

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
                //.setSupportActionBar(toolbar)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        button = view.findViewById(R.id.btnArrowBackMain)

        button.setOnClickListener{
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
        }

        adapter.setItem(listOf(
                Company(
                    1,
                    "Empresa 1",
                    "Exemplo teste",
                    "https://thispersondoesnotexist.com/image",
                    "Brasil",
                    CompanyType (
                             0,
                             "valor"
                    )
                ),
                Company(
                         1,
                         "Empresa 2",
                         "Exemplo teste",
                         "https://thispersondoesnotexist.com/image",
                         "Brasil",
                        CompanyType (
                                0,
                                "valor"
                        )
                ),
                Company(
                         1,
                         "Empresa 3",
                         "Exemplo teste",
                         "https://thispersondoesnotexist.com/image",
                         "Brasil",
                        CompanyType (
                                 0,
                                 "valor"
                        )
                )
            )
        )

    }

    private fun clickItem(company: Company){
        findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailsFragment(
                        name = company.companyName,
                        imageUrl = company.pathImage,
                        description = company.description
                )
        )
    }
}