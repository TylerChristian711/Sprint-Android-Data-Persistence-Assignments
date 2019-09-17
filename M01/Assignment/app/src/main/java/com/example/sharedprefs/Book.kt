package com.example.sharedprefs

import android.icu.text.CaseMap
import java.io.Serializable

class Book(val id:String,val name:String,val reasonToRead:String,isFavorite:Boolean)
fun toList(id:String,name: String,reasonToRead: String,isFavorite: Boolean){
    return toList("$id","$name","$reasonToRead",false)

    fun TakeStringListReturnMap(var myList:List<String>) {
        val myMap = mutableMapOf<String, String>()
        myList.associateTo(myMap) {it to it}
    }
    fun csvToString(var myCSV){
        return Book(myCSV)
    }

}

