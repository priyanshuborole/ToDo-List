package com.example.notesapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notesapp.database.UserDatabase
import com.example.notesapp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {

    companion object{
        private var userDatabase: UserDatabase?=null
        fun initialiseDB(context: Context): UserDatabase? {
            return UserDatabase.getInstance(context)
        }
        fun insert(context: Context,user : User){
        userDatabase = initialiseDB(context)
            CoroutineScope(IO).launch {
                userDatabase?.getDao()?.insert(user)
            }
        }

        fun getAllUserData(context: Context): LiveData<List<User>>? {
            userDatabase= initialiseDB(context)
            return userDatabase?.getDao()?.getAllUserData()
        }
        fun deleteTask(context: Context,user: User) {
            userDatabase = initialiseDB(context)
            CoroutineScope(IO).launch {
                userDatabase?.getDao()?.deleteTask(user)
            }
        }

        fun deleteUser(context: Context,userId: Int) {
            userDatabase = initialiseDB(context)
            CoroutineScope(IO).launch {
                userDatabase?.getDao()?.deleteUser(userId)
            }
        }

    }


}