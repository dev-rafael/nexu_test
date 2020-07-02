package com.rgdev.test_nexu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.TextView
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
    private lateinit var modelAdapter: MyAdapter
    private lateinit var marcaAdapter: ArrayAdapter<String>

    private class MyAdapter(var myDataset: Array<ModelListItem>, var removeFun: (Int)->Unit): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val marca: TextView
            val modelo: TextView
            val precio: TextView
            val remove: ImageButton

            init {
                marca = view.textView_marca_item
                modelo = view.textView_modelo_item
                precio = view.textView_precio_item
                remove = view.imageButton_remove_item
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): MyViewHolder {
            // create a new view
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.model_list_item, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return myDataset.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.marca.text = myDataset[position].marca
            holder.modelo.text = myDataset[position].modelo
            holder.precio.text = myDataset[position].precio.toString()
            holder.remove.setOnClickListener {
                removeFun(holder.adapterPosition)
            }
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
        //val testList = arrayOf("Item 1", "Item 2", "Item3")
        marcaAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, viewModel.listaMarcas)
        textInput_seleccionar_marca.setAdapter(marcaAdapter)


        recyclerView = recyclerView_lista_modelos
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            //itemAnimator = null
        }
        modelAdapter = MyAdapter(viewModel.listaModelos.toTypedArray(), ::removeItem)
        recyclerView.adapter = modelAdapter

        button_agregar_marca.setOnClickListener {
            val text = textInput_agregar_marca.text.toString()
            val noMarca = text.isEmpty()

            textLayout_agregar_marca.error = if(noMarca) "* Campo requerido" else null

            if (!noMarca){
                viewModel.listaMarcas.add(text)
                textInput_agregar_marca.setText("")
                Log.i("Lista Marcas", viewModel.listaMarcas.toString())
                //textInput_seleccionar_marca.autofill(viewModel.listaMarcas.toTypedArray<String>())
            }
        }

        button_agregar_modelo.setOnClickListener {
            val marca = textInput_seleccionar_marca.text.toString()
            val modelo = textInput_modelo.text.toString()
            val precio = textInput_precio.text.toString()

            val noMarca = marca.isEmpty()
            val noModelo = modelo.isEmpty()
            val noPrecio = precio.isEmpty()

            textLayout_seleccionar_marca.error = if(noMarca) "* Campo requerido" else null
            textLayout_modelo.error = if(noModelo) "* Campo requerido" else null
            textLayout_precio.error = if(noPrecio) "* Campo requerido" else null

            if(!noMarca && !noModelo && !noPrecio){
                textInput_seleccionar_marca.setText("")
                textInput_modelo.setText("")
                textInput_precio.setText("")

                if (viewModel.listaModelos.isEmpty()){
                    linearLayout_list_header.visibility = View.VISIBLE
                }

                viewModel.listaModelos.add(ModelListItem(marca, modelo, precio.toInt()))
                modelAdapter.myDataset = viewModel.listaModelos.toTypedArray()
                modelAdapter.notifyItemInserted(viewModel.listaModelos.size-1)

            }
            Log.i("Lista Modelos", viewModel.listaModelos.toString())
        }

    }

    fun removeItem(index: Int){
        viewModel.listaModelos.removeAt(index)
        modelAdapter.myDataset = viewModel.listaModelos.toTypedArray()
        modelAdapter.notifyItemRemoved(index)
        if (viewModel.listaModelos.isEmpty()){
            linearLayout_list_header.visibility = View.INVISIBLE
        }
    }

}
