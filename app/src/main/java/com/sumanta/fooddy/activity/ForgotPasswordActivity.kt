package com.sumanta.fooddy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.sumanta.fooddy.R

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var etMobile:EditText
    lateinit var etEmail:EditText
    lateinit var btnNext:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Forgot Password"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        etMobile = findViewById(R.id.etForgotMobile)
        etEmail = findViewById(R.id.etforgotEmail)
        btnNext = findViewById(R.id.btnForgotNext)

       /* btnNext.setOnClickListener {

         /*   val intent = Intent(this@ForgotPasswordActivity, DashboardActivity::class.java)
            val bundle = Bundle()


            bundle.putString("data", "forgot")

            bundle.putString("mobile", etMobile.text.toString())
            bundle.putString("email", etEmail.text.toString())

            intent.putExtra("details", bundle)

            startActivity(intent)*/
        }*/
    }
}
