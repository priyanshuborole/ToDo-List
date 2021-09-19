package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.adapter.UserAdapter
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.model.User
import com.example.notesapp.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(), UserAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var title: EditText
    private lateinit var task: EditText
    private lateinit var save: Button
    private lateinit var del: Button
    private lateinit var userAdapter: UserAdapter
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.splashScreen)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userAdapter = UserAdapter(this, ArrayList<User>(), this)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter= userAdapter
        }
        userViewModel = ViewModelProvider(this)
            .get(UserViewModel::class.java)
        userViewModel.getAllUserData(applicationContext)?.observe(this, Observer {
            userAdapter.setData(it as ArrayList<User>)


        })
        binding.addBtn.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        builder = AlertDialog.Builder(this)
        val itemView: View =
            LayoutInflater.from(applicationContext).inflate(R.layout.dialog_layout, null)
        dialog = builder.create()
        dialog.setView(itemView)
        title = itemView.findViewById(R.id.enterTitle)
        task = itemView.findViewById(R.id.enterTask)
        save = itemView.findViewById(R.id.save_button)

        save.setOnClickListener {
            saveDataInRoomDataBase()
        }
        dialog.show()
    }


    private fun saveDataInRoomDataBase() {
        val getTitle = title.text.toString().trim()
        val getTask = task.text.toString().trim()

        if (!TextUtils.isEmpty(getTitle) && !TextUtils.isEmpty(getTask)){
            userViewModel.insert(this,User(getTitle,getTask))
            dialog.dismiss()
        }
        else{
            Toast.makeText(applicationContext,"Fill all the Fields",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(user: User) {
        openDialog2(user)
    }

    private fun openDialog2(user: User) {
        builder = AlertDialog.Builder(this)
        val itemView: View =
            LayoutInflater.from(this@MainActivity).inflate(R.layout.update_dialog, null)
        dialog = builder.create()
        dialog.setView(itemView)
        del = itemView.findViewById(R.id.del_button)
        del.setOnClickListener{
            //userViewModel.deleteUser(this,userId) //delete using id
            userViewModel.deleteTask(this,user)
            dialog.dismiss()
        }
        dialog.show()
    }
}


