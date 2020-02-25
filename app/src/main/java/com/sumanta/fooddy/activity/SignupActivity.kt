package com.sumanta.fooddy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import com.sumanta.fooddy.DashboardActivity
import com.sumanta.fooddy.R

class SignupActivity : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var btnSingup:Button
    lateinit var etName : EditText
    lateinit var etEmail : EditText
    lateinit var etMobileNumber:EditText
    lateinit var etAddress : EditText
    lateinit var etPassword:EditText
    lateinit var etConfirmPassword:EditText
    lateinit var rlRegister:RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "SineUp"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rlRegister = findViewById(R.id.rlRegister)
        btnSingup = findViewById(R.id.btnRegister)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
       etMobileNumber = findViewById(R.id.etMobileNumber)
        etAddress = findViewById(R.id.etAddress)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)

        //button
       /* btnSingup.setOnClickListener {
            val intent = Intent(this@SignupActivity, DashboardActivity::class.java)
            val bundle = Bundle()

            bundle.putString("data","signup")

            bundle.putString("name",etName.text.toString())
            bundle.putString("email",etEmail.text.toString())
            bundle.putString("number",etMobileNumber.text.toString())
            bundle.putString("address",etAddress.text.toString())
            bundle.putString("password",etPassword.text.toString())


            intent.putExtra("details",bundle)
            startActivity(intent)
        }*/
    }
}
