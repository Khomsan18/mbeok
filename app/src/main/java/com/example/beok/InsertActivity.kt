package com.example.beok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.beok.databinding.ActivityInsertBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InsertActivity : AppCompatActivity() {
    private lateinit var bindingInsert : ActivityInsertBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInsert = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(bindingInsert.root)
    }

    fun addEbook(v: View) {
        var  type_str :String = ""
        if (bindingInsert.radioFantasy.isChecked){
            type_str += "  " + bindingInsert.radioFantasy.text;
        }
        if (bindingInsert.radioRomance.isChecked){
            type_str += "  " + bindingInsert.radioRomance.text;
        }
        if (bindingInsert.radioSciFi.isChecked){
            type_str += "  " + bindingInsert.radioSciFi.text;
        }
        if (bindingInsert.radioComedy.isChecked){
            type_str += "  " + bindingInsert.radioComedy.text;
        }
        if (bindingInsert.radioCartoon.isChecked){
            type_str += "  " + bindingInsert.radioCartoon.text;
        }
        var type_new = if (type_str.isNotEmpty()) type_str else "No Type."
        val api: EbookAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EbookAPI::class.java)
        api.insertEbk(
            bindingInsert.edtName.text.toString(),
            bindingInsert.edtPublisher.text.toString(),
            bindingInsert.edtPrice.text.toString().toInt(),
            type_new

        ).enqueue(object : Callback<Ebook> {
            override fun onResponse(
                call: Call<Ebook>,
                response: Response<Ebook>
            ) {
                if (response.isSuccessful()) {
                    Toast.makeText(applicationContext,"Successfully Inserted", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Ebook>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    fun resetEbook(v: View) {
        bindingInsert.edtName.text?.clear()
        bindingInsert.edtPublisher.text?.clear()
        bindingInsert.edtPrice.text?.clear()
    }
}