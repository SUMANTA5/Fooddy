package com.sumanta.fooddy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.sumanta.fooddy.DashboardActivity
import com.sumanta.fooddy.R

class LoginActivity : AppCompatActivity() {

    private lateinit var registerYourself : TextView
    private lateinit var login: Button
     lateinit var etMobileNumber: EditText
     lateinit var etPassword: EditText
    private lateinit var txtForgotPassword: TextView

    //val validMobileNumber = "9088705526"
    //val validPassword = "914320"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //findingBy id
        etMobileNumber = findViewById(R.id.etMobileNumbet)
        etPassword = findViewById(R.id.etPassword)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        registerYourself = findViewById(R.id.txtRegisterYourself)
        login = findViewById(R.id.txtButton)


        //onclick fun
        txtForgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
        registerYourself.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }

        //onclick fun in login
        login.setOnClickListener {

            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)

            startActivity(intent)
            //val mobileNumber = etMobileNumber.text.toString()
           // val passWord = etPassword.text.toString()

           /* if ((mobileNumber==validMobileNumber)&&(passWord==validPassword)) {

               // val nameOfTitle = "Sumanta"

                val intent = Intent(this@LoginActivity, DashboardActivity::class.java)

               // intent.putExtra("name",nameOfTitle)
                //start new activity
                startActivity(intent)
            }else{
                Toast.makeText(this@LoginActivity,"Incorrect Password",Toast.LENGTH_SHORT).show()
            }*/

        }
    }

   /* override fun onPause() {
        super.onPause()
        finish()
    }*/
}
