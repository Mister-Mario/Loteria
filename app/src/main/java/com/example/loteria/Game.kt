package com.example.loteria

class Game {
    companion object{
        private val names : MutableList<String> = mutableListOf()
        private lateinit var money : MutableList<MutableList<Double>>
        private var prizes : MutableMap<String, List<Double>> = mutableMapOf()
        private var lineaPrice : Double = 0.0
        private var bingoPrice : Double = 0.0

        private val ganadores : MutableList<String> = mutableListOf()

        fun addName(name:String){
            names.add(name)
        }

        fun startGame(){
            money = MutableList(names.size){
                index: Int -> MutableList(names.size){index: Int -> 0.0 }
            }
            for(name : String in names){
                prizes[name] = MutableList(names.size){index: Int -> 0.0 }
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

        fun addGanador(name: String){
            if(!ganadores.contains(name))
                ganadores.add(name)
        }

        fun clearGanadores(){
            ganadores.clear()
        }

        fun setGanador(playingLinea: Boolean) {
            if (playingLinea)
                setGanador(lineaPrice)
            else
                setGanador(bingoPrice)
        }
        private fun setGanador(price: Double){
            for (name in ganadores)
                setGanador(price/ ganadores.size, name)
        }

        private fun setGanador(price: Double, name: String){
            val i = names.indexOf(name)
            for (j in money.indices){
                if(i != j)
                    money[i][j] += price
            }
        }

        fun getPrizes(name: String): List<String>? {
            return prizes[name]?.mapIndexed{ index, prize ->
                if (prize > 0)
                    "Se debe a " + names[index] +  ": " + prize.toString() + "€"
                else{
                    "No se debe nada a " + names[index]
                }
            }
        }

        fun computePrizes() {
            for(name in names){
                prizes[name] = setPrizes(name)
            }
        }

        private fun setPrizes(player: String): List<Double> {
            val indexOfPlayer = names.indexOf(player)
            val list : MutableList<Double> = mutableListOf()
            var debtFromPlayerToName : Double
            var debtFromNameToPlayer : Double
            for (nameIndex in names.indices){
                if(nameIndex != indexOfPlayer){
                    debtFromNameToPlayer = money[indexOfPlayer][nameIndex]
                    debtFromPlayerToName = money[nameIndex][indexOfPlayer]
                    if(debtFromPlayerToName > debtFromNameToPlayer)
                        list.add(debtFromPlayerToName - debtFromNameToPlayer)
                    else{
                        list.add(0.0)
                    }
                }
                else{
                    list.add(0.0)
                }
            }
            return list
        }

        fun reset(){
            names.clear()
            prizes.clear()
            money.clear()
        }
    }
}