package com.example.empresas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.DialogFragment
import com.example.empresas.R

class DialogFragment(private var onYesEvent: () -> Unit = {}, private var onNoEvent: () -> Unit = {}) : DialogFragment() {

    private lateinit var btnYes: AppCompatButton
    private lateinit var btnNo: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnYes = view.findViewById(R.id.buttonYes)
        btnNo = view.findViewById(R.id.buttonNo)

        btnYes.setOnClickListener { onYesEvent() }
        btnNo.setOnClickListener { onNoEvent() }

    }

}