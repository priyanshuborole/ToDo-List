package com.example.notesapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.model.User
import com.example.notesapp.repository.UserRepository

class UserViewModel:ViewModel(){
    fun insert(context: Context,user: User){
        UserRepository.insert(context, user)
    }
    fun getAllUserData(context: Context):LiveData<List<User>>?{
        return UserRepository.getAllUserData(context)

    }
    fun deleteTask(context: Context,user: User){
        UserRepository.deleteTask(context, user)
    }
    fun deleteUser(context: Context,userId: Int){
        UserRepository.deleteUser(context, userId)
    }

}