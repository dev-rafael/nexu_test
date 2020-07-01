package com.rgdev.test_nexu

import androidx.lifecycle.ViewModel

class FormularioViewModel : ViewModel() {
    val listaMarcas: MutableList<String>
    val listaModelos: MutableList<ModelListItem>

    init {
        listaMarcas = mutableListOf("a")
        listaModelos = mutableListOf()
    }

}
