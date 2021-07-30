package com.example.empresas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.empresas.databinding.FragmentDialogBinding

class DialogFragment(
    private var onYesEvent: () -> Unit = {},
    private var onNoEvent: () -> Unit = {}
) : DialogFragment() {

    private lateinit var binding: FragmentDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonYes.setOnClickListener { onYesEvent() }
        binding.buttonNo.setOnClickListener { onNoEvent() }

    }

}