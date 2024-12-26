package com.example.loteria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loteria.Game
import com.example.loteria.R
import com.example.loteria.databinding.FragmentPricesBinding

class Prices : Fragment() {

    private var firstPriceSet : Boolean = false
    private lateinit var binding: FragmentPricesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPricesBinding.inflate(inflater, container, false)



        binding.btnPrice.setOnClickListener{
            val price : Double
            val priceStr : String = binding.price.text.toString()
            if(priceStr.isNumber()){
                price = priceStr.toDouble()
                if(firstPriceSet){
                    Game.setBingoPrice(price)
                    findNavController().navigate(PricesDirections.actionPricesToRound())
                }
                else{
                    this.firstPriceSet = true
                    Game.setLineaPrice(price)
                    binding.infoPrices.text = "Introducir precio Bingo"
                    binding.price.setText("")
                }
            }
            else{
                Toast.makeText(requireContext(), "El precio a de ser un número positivo", Toast.LENGTH_LONG).show()
            }


        }

        return binding.root
    }

    private fun String.isNumber(): Boolean {
        return try {
            val price = this.toDouble()
            return price >= 0
        } catch (e: NumberFormatException) {
            false
        }
    }
}