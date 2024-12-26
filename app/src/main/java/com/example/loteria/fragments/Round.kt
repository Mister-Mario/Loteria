package com.example.loteria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loteria.Game

import com.example.loteria.R
import com.example.loteria.databinding.FragmentRoundBinding

class Round : Fragment() {

    private var playingLinea : Boolean = true

    private lateinit var binding : FragmentRoundBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRoundBinding.inflate(inflater, container, false)

        binding.recyclerNamesRound.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.recyclerNamesRound.adapter = NombresAdapter(Game.getNames(), "Ganador"){
            name : String -> Game.setGanador(playingLinea, name)
            this@Round.nextRound()
        }


        return binding.root
    }

    private fun nextRound(){
        if (this@Round.playingLinea){
            this@Round.playingLinea = false
            this@Round.binding.txtRound.text = "Jugando Bingo"
        }
        else
            findNavController().navigate(RoundDirections.actionRoundToEndRound())

    }

}