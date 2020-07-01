package com.rgdev.test_nexu

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.formulario_fragment.*
import kotlinx.android.synthetic.main.model_list_item.view.*


class FormularioFragment : Fragment() {

    companion object {
        fun newInstance() = FormularioFragment()
    }

    private lateinit var viewModel: FormularioViewModel
    private lateinit var recyclerView: RecyclerView

    private class MyAdapter(private val myDataset: Array<ModelListItem>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){
            val marca: TextView
            val modelo: TextView
            val precio: TextView

            init {
                marca = view.textView_marca_item
                modelo = view.textView_modelo_item
                precio = view.textView_precio_item
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): MyViewHolder {
            // create a new view
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.formulario_fragment, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return myDataset.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.marca.text = myDataset[position].marca
            holder.modelo.text = myDataset[position].modelo
            holder.precio.text = myDataset[position].precio.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.formulario_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormularioViewModel::class.java)

        recyclerView = recyclerView_lista_modelos
        recyclerView.adapter = MyAdapter(viewModel.listaModelos.toTypedArray())

        button_agregar_marca.setOnClickListener {
            val text = textInput_agregar_marca.text.toString()
            if (!text.isEmpty()){
                viewModel.listaMarcas.add(text)
                Log.i("Lista Marcas", viewModel.listaMarcas.toString())
                //textInput_seleccionar_marca.autofill(viewModel.listaMarcas.toTypedArray<String>())
            }
        }

        button_agregar_modelo.setOnClickListener {
            val marca = textInput_seleccionar_marca.text.toString()
            val modelo = textInput_modelo.text.toString()
            val precio = textInput_precio.text.toString()
            if(!marca.isEmpty() && !modelo.isEmpty() && !precio.isEmpty()){
                viewModel.listaModelos.add(ModelListItem(marca, modelo, precio.toInt()))

                Log.i("Lista Modelos", viewModel.listaModelos.toString())

            }
        }

    }

}
