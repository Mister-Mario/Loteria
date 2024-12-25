package com.example.loteria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loteria.Game
import com.example.loteria.databinding.FragmentAddNamesBinding

class AddNames : Fragment() {

    private lateinit var binding : FragmentAddNamesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNamesBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.recyclerView.adapter = NombresAdapter(Game.getNames(), "Eliminar"){
                name : String -> Game.deleteName(name)
        }

        binding.addName.setOnClickListener{
            val name = binding.name.text.toString()
            if(name.isBlank())
                Toast.makeText(context, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            else{
                Game.addName(name)

                (binding.recyclerView.adapter as NombresAdapter).updateNombres(Game.getNames())

                binding.name.setText("")
            }

        }

        binding.startGame.setOnClickListener{
            Game.startGame()
            findNavController().navigate(AddNamesDirections.actionAddNamesToPrices())
        }



        return root
    }
}