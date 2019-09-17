package com.example.sharedprefs

import android.icu.text.CaseMap
import java.io.Serializable

class Book:Serializable{

    var title:String? = null
    var reasonToRead:String? = null
    var hasBeenRead:Boolean = false
    var id:String? = null


    constructor(title:String,reasonToRead:String,hasBeenRead:Boolean,id:String){
        this.title = title
        this.reasonToRead = reasonToRead
        this.hasBeenRead = false
        this.id = id

    }



}
