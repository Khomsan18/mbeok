package com.example.beok

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beok.databinding.EditDeleteItemLayoutBinding

class EditUserAdapter(val items :List<User>, val context: Context):
    RecyclerView.Adapter<EditUserAdapter.ViewHolder>() {

    inner class ViewHolder(view: View, val binding: EditDeleteItemLayoutBinding) :
        RecyclerView.ViewHolder(view) {
        init {
            binding.tvEditDelete.setOnClickListener {
                val item = items[adapterPosition]
                val contextView:Context = view.context
                val intent = Intent(contextView, EditDeleteActivity::class.java)
                intent.putExtra("user_id",item.user_id.toString().toInt())
                intent.putExtra("username",item.username)
                intent.putExtra("password",item.password)
                intent.putExtra("email",item.email)
                intent.putExtra("tel",item.tel)
                intent.putExtra("fname",item.fname)
                intent.putExtra("lname",item.lname)
                contextView.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EditDeleteItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding
        binding_holder.tvID.text = items[position].user_id.toString()
        binding_holder.tvName.text = items[position].username
    }

    override fun getItemCount(): Int {
        return items.size
    }
}