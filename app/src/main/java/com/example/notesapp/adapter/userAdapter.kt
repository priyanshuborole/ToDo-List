package com.example.notesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.model.User

class UserAdapter(private val context:Context, private var userList:ArrayList<User>, private var myItemClickListener: ItemClickListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_layout,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user:User = userList[position]
        holder.name.text = user.name
        holder.task.text = user.note

    }

    fun setData(userList: ArrayList<User>){
        this.userList=userList
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = userList.size

    interface ItemClickListener{
        fun onItemClick(user: User)
    }

    inner class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
    val name:TextView = itemView.findViewById(R.id.todoName)
    val task:TextView = itemView.findViewById(R.id.todoTask)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position!=RecyclerView.NO_POSITION) {
                myItemClickListener.onItemClick(userList[position])
            }
        }
    }
}
