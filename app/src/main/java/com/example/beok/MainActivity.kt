package com.example.beok

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beok.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var userList = arrayListOf<User>()
    val createClient = UserAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root )

        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.recyclerView.context,
                DividerItemDecoration.VERTICAL) )
    }
    override fun onResume() {
        super.onResume()
        callUser()
    }

    fun callUser(){
        binding.edtSearch.text?.clear()
        userList.clear()
        createClient.retrieveUser()
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    response.body()?.forEach {
                        userList.add(User(it.user_id, it.username, it.password, it.email, it.tel, it.fname, it.lname))
                    }
                    binding.recyclerView.adapter = EditUserAdapter(userList, applicationContext)
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable){
                    t.printStackTrace()
                    Toast.makeText(applicationContext,"Error2", Toast.LENGTH_LONG).show()
                }
            })
    }

    fun clickSearch(v: View){
        userList.clear()
        if(binding.edtSearch.text!!.isEmpty()){
            callUser()
        }else{
            createClient.retrieveUserID(binding.edtSearch.text.toString())
                .enqueue(object :Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if(response.isSuccessful){
                            userList.add(User(response.body()?.user_id.toString(),
                                response.body()?.username.toString(),
                                response.body()?.password.toString(),
                                response.body()?.email.toString(),
                                response.body()?.tel.toString(),
                                response.body()?.fname.toString(),
                                response.body()?.lname.toString(),


                            ))
                            binding.recyclerView.adapter= EditUserAdapter(userList,applicationContext)
                        }else{
                            Toast.makeText(applicationContext,"User ID Not Found",
                                Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(applicationContext,"Error onFailure " + t.message,
                            Toast.LENGTH_LONG).show()
                    }
                })
        }
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