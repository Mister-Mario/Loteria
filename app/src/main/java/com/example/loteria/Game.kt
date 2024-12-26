package com.example.loteria

class Game {
    companion object{
        private val names : MutableList<String> = mutableListOf()
        private lateinit var money : List<MutableList<Double>>

        private var lineaPrice : Double = 0.0
        private var bingoPrice : Double = 0.0

        fun addName(name:String){
            names.add(name)
        }

        fun startGame(){
            money = List(names.size){
                index: Int -> MutableList(names.size){index: Int -> 0.0 }
            }
        }

        fun getNames() : List<String>{
            return names.toList()
        }

        fun deleteName(name:String){
            names.remove(name)
        }

        fun setLineaPrice(price:Double){
            if (price >= 0)
                this.lineaPrice = price
        }

        fun setBingoPrice(price:Double){
            if (price >= 0)
                this.bingoPrice = price
        }

        fun setGanador(playingLinea: Boolean, name: String) {
            if (playingLinea)
                setGanador(lineaPrice, name)
            else
                setGanador(bingoPrice, name)
        }

        private fun setGanador(price: Double, name: String){
            val i = names.indexOf(name)
            for (j in money.indices){
                if(i != j)
                    money[i][j] += price
            }
        }

        fun getMoney() : List<List<Double>>{
            return money
        }
    }
}