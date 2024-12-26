package com.example.loteria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loteria.Game
import com.example.loteria.R
import com.example.loteria.databinding.FragmentPricesBinding
import com.example.loteria.databinding.FragmentPrizesBinding


class Prizes : Fragment() {

    private lateinit var binding: FragmentPrizesBinding
    private val players = Game.getNames()
    private var playerCounter = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Game.computePrizes()
        binding = FragmentPrizesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateView(view)
        binding.btnPrizes.setOnClickListener{
            this.updateView(view)
        }
    }

    private fun updateView(view: View){
        if(playerCounter == players.size){
            Game.reset()
            findNavController().navigate(PrizesDirections.actionPrizesToAddNames())
        }
        else{
            val name = players[playerCounter]
            binding.txtPlayer.text = "$name ha de pagar: "
            binding.recyclerPrizes.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerPrizes.adapter = NombresAdapter(Game.getPrizes(name)!!, null){  }
            playerCounter += 1
            view.invalidate()
        }

    }
}