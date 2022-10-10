package com.example.beok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beok.databinding.EbkItemLayoutBinding


class EbooksAdapter(val ebookList : ArrayList<Ebook>?, val context: Context)
    : RecyclerView.Adapter<EbooksAdapter.ViewHolder>() {

    inner class ViewHolder (view: View, val binding: EbkItemLayoutBinding) :
        RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EbkItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        binding.ebookName.text = "ชื่อ: " + ebookList!![position].ebook_name
        binding.ebookPublisher.text = "นักเขียน: " +ebookList!![position].ebook_publisher
        binding.ebookPrice.text = "ราคา: " +ebookList!![position].ebook_price.toString()
        binding.ebookType.text = "หมวดหมู่: " +ebookList!![position].ebook_type
    }

    override fun getItemCount(): Int {
        return  ebookList!!.size
    }
}