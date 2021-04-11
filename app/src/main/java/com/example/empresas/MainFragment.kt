package com.example.empresas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.empresas.remote.CompanyService
import com.example.empresas.remote.GetCompaniesResponse
import com.example.empresas.remote.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragment : Fragment() {

    private val args: MainFragmentArgs by navArgs()

    private val adapter by lazy { CompanyAdapter(::clickItem)}
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    //lateinit var button: AppCompatButton

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
        //button = view.findViewById(R.id.btnArrowBackMain)

        getCompanies()

    }

    private fun getCompanies() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = CompanyService.newInstance().getEnterprises(
                    accessToken = args.accessToken,
                    client = args.clientId,
                    uid = args.uid
            )

            handleResponse(response)
        }
    }

    private fun handleResponse(response: Response<GetCompaniesResponse>) {
        if(response.isSuccessful) {
            adapter.setItems(response.body()?.companies?.map { it.toModel() } ?: listOf())
        }
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