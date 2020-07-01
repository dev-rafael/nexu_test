package com.rgdev.test_nexu

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class FormularioFragment : Fragment() {

    companion object {
        fun newInstance() = FormularioFragment()
    }

    private lateinit var viewModel: FormularioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.formulario_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FormularioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
