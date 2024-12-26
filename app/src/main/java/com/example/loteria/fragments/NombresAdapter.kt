package com.example.loteria.fragments

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loteria.Game
import com.example.loteria.R

class NombresAdapter(
    listaNombres: List<String>,
    private val buttonText: String?,
    private val buttonClick: (name:String) -> Unit
): RecyclerView.Adapter<NombresAdapter.ViewHolder>() {

    private val listaNombres : MutableList<String> = listaNombres.toMutableList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutElemento = R.layout.nombre_item
        val view = LayoutInflater.from(viewGroup.context).inflate(layoutElemento, viewGroup, false)
        return ViewHolder(view, this, buttonText, buttonClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listaNombres[position])
    }

    override fun getItemCount(): Int {
        return listaNombres.size
    }

    fun updateNombres(nombres: List<String>) {
        val oldSize = listaNombres.size
        listaNombres.clear()
        listaNombres.addAll(nombres)
        //Si tenemos menos tareas hay que avisar al adapter
        if (oldSize > listaNombres.size)
            notifyItemRangeRemoved(listaNombres.size, oldSize - listaNombres.size)

        notifyItemRangeChanged(0, listaNombres.size)
    }

    class ViewHolder(
        private val view:View,
        private val nombresAdapter: NombresAdapter,
        private val buttonText: String?,
        private val buttonClick: (name:String) -> Unit
    ):RecyclerView.ViewHolder(view){
        private val nombre = view.findViewById<TextView>(R.id.nombreItem)
        private val button = view.findViewById<Button>(R.id.buttonItem)

        fun bind(nombre:String){
            this.nombre.text = nombre
            setUpButton()
        }

        private fun setUpButton(){
            button.text = buttonText
            buttonText?.let { buttonText ->
                button.visibility = View.VISIBLE
                button.setOnClickListener{
                    buttonClick(this.nombre.text.toString())
                    this.nombresAdapter.updateNombres(Game.getNames())
                    this.view.invalidate()
                }
            }
        }
    }
}