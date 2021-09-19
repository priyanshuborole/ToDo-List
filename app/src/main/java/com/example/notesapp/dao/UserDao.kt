package com.example.notesapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user : User)
    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getAllUserData(): LiveData<List<User>>
    @Delete
    suspend fun deleteTask(user : User)
    @Query("DELETE FROM user WHERE id = :userId ")
    fun deleteUser(userId: Int)

}