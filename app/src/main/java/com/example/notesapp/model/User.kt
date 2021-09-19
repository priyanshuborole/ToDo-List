package com.example.notesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (val name:String, val note: String) {

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null

}