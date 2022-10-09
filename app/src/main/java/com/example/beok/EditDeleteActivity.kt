package com.example.beok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.beok.databinding.ActivityEditDeleteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditDeleteActivity : AppCompatActivity() {
    private lateinit var bindingEditDelete : ActivityEditDeleteBinding
    val createClient = UserAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingEditDelete = ActivityEditDeleteBinding.inflate(layoutInflater)
        setContentView(bindingEditDelete.root)

        val mID = intent.getStringExtra("user_id")
        val mUsername = intent.getStringExtra("username")
        val mPassword = intent.getStringExtra("password")
        val mFname = intent.getStringExtra("fname")
        val mLname = intent.getStringExtra("lname")
        val mEmail = intent.getStringExtra("email")
        val mTel = intent.getStringExtra("tel")

        bindingEditDelete.edtID.setText(mID)
        bindingEditDelete.edtID.isEnabled = false
        bindingEditDelete.edtUsername.setText(mUsername)
        bindingEditDelete.edtPassword.setText(mPassword)
        bindingEditDelete.edtFname.setText(mFname)
        bindingEditDelete.edtLname.setText(mLname)
        bindingEditDelete.edtEmail.setText(mEmail)
        bindingEditDelete.edtTel.setText(mTel)
    }

    fun saveUser(v: View) {
        createClient.updateUser(
            bindingEditDelete.edtID.text.toString(),
            bindingEditDelete.edtUsername.text.toString(),
            bindingEditDelete.edtPassword.text.toString(),
            bindingEditDelete.edtFname.text.toString(),
            bindingEditDelete.edtLname.text.toString(),
            bindingEditDelete.edtEmail.text.toString(),
            bindingEditDelete.edtTel.text.toString(),
        ).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText( applicationContext, "Seccessfully Updated",
                        Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, " Update Failure",
                        Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message,
                    Toast.LENGTH_LONG).show()
            }
        })
    }

    fun deleteUser(v: View) {
        val myBundle = AlertDialog.Builder(this)
        myBundle.apply {
            setTitle("Warning Message")
            setMessage("Do you want to delete the User?")
            setNegativeButton("Yes"){dialog,which ->
                createClient.deleteUser(bindingEditDelete.edtID.text.toString()).enqueue(object :
                    Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            Toast.makeText( applicationContext, "Seccessfully Delete",
                                Toast.LENGTH_LONG).show()
                            finish()
                        } else {
                            Toast.makeText(applicationContext, " Delete Failure",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(applicationContext,"Error onFailure " + t.message,
                            Toast.LENGTH_LONG).show()
                    }
                })
            }
            setPositiveButton("No"){_,_->
            }
            show()
        }

    }
}