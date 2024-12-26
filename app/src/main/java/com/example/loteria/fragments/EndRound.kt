package com.example.loteria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loteria.R
import com.example.loteria.databinding.FragmentEndRoundBinding

class EndRound : Fragment() {

    private lateinit var binding: FragmentEndRoundBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEndRoundBinding.inflate(inflater, container, false)

        binding.btnPlayAgain.setOnClickListener{
            findNavController().navigate(EndRoundDirections.actionEndRoundToRound())
        }

        binding.btnFinish.setOnClickListener{
            findNavController().navigate(EndRoundDirections.actionEndRoundToPrizes())
        }

        return binding.root
    }
}