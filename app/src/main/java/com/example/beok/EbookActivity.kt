package com.example.beok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beok.databinding.ActivityEbookBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EbookActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEbookBinding
    var ebookList  = arrayListOf<Ebook>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEbookBinding.inflate(layoutInflater)
        setContentView(binding.root )
        // Link to RecyclerView
        binding.recyclerView.adapter = EbooksAdapter(this.ebookList, applicationContext)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.recyclerView.getContext(),
                DividerItemDecoration.VERTICAL)
        )
    }

    override fun onResume() {
        super.onResume()
        callEbookData()
    }

    fun callEbookData(){
        ebookList.clear();
        val serv : EbookAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EbookAPI ::class.java)

        serv.retrieveEbook()
            .enqueue(object : Callback<List<Ebook>> {
                override fun onResponse(call: Call<List<Ebook>>, response: Response<List<Ebook>>) {
                    response.body()?.forEach {
                        ebookList.add(Ebook(it.ebook_name, it.ebook_publisher,it.ebook_price,it.ebook_type))
                    }
                    //// Set Data to RecyclerRecyclerView
                    binding.recyclerView.adapter = EbooksAdapter(ebookList,applicationContext)
                    binding.txt1.text = "รายการ E-book : "+ ebookList.size.toString()
                }
                override fun onFailure(call: Call<List<Ebook>>, t: Throwable) {
                    Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
    fun addEbook(v: View) {
        val intent = Intent(applicationContext, InsertActivity::class.java)
        startActivity(intent)
    }
    fun onClickEditEbook(v: View) {
        val intent = Intent(applicationContext, EbookActivity::class.java)
        startActivity(intent)
    }
    fun onClickEditUser(v: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}