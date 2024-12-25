package com.example.loteria

class Game {
    companion object{
        private val names : MutableList<String> = mutableListOf()
        private lateinit var money : List<MutableList<Int>>

        fun addName(name:String){
            names.add(name)
        }

        fun startGame(){
            money = List(names.size){
                index: Int -> MutableList(names.size){index: Int -> 0 }
            }
        }

        fun getNames() : List<String>{
            return names.toList()
        }

        fun deleteName(name:String){
            names.remove(name)
        }
    }
}