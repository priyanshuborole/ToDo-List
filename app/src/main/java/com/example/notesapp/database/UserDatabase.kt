package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.dao.UserDao
import com.example.notesapp.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

     abstract  fun getDao():UserDao

     companion object{
         private const val DATABASE_NAME = "UserDatabase"

         @Volatile
         var instance:UserDatabase?=null

         fun getInstance(context: Context): UserDatabase?{

             if (instance == null){
                 synchronized(UserDatabase::class.java){
                     if (instance==null){
                         instance= Room.databaseBuilder(context,
                             UserDatabase::class.java,
                             DATABASE_NAME)
                             .build()
                     }
                 }
             }
             return instance
         }
     }

}