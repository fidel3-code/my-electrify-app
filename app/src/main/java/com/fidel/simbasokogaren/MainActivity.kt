package com.fidel.simbasokogaren

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }



        val signin = findViewById<Button>(R.id.signin)
        signin.setOnClickListener {

            val signinIntent= Intent(applicationContext,SignIn::class.java)
            startActivity(signinIntent)
        }

        val signup = findViewById<Button>(R.id.signup)
        signup.setOnClickListener {

            val signupIntent= Intent(applicationContext, SignUp::class.java)
            startActivity(signupIntent)
        }

        val about=findViewById<Button>(R.id.aboutinfo)
        about.setOnClickListener {
            val aboutIntent= Intent(applicationContext, About::class.java)
            startActivity(aboutIntent)
        }

        val progressbar=findViewById<ProgressBar>(R.id.progressbar)
        val recyclerview=findViewById<RecyclerView>(R.id.recyclerview)

        val api="http://fidel.alwaysdata.net/api/getproductdetails"

        val helper= ApiHelper(applicationContext)
        helper.loadProducts(api,recyclerview,progressbar)
    }

//    http://fidel.alwaysdata.net/api/signin
//    http://fidel.alwaysdata.net/api/signup
//    http://fidel.alwaysdata.net/api/getproductdetails
//    http://fidel.alwaysdata.net/api/mpesa_payment
//    http://fidel.alwaysdata.net/api/addproducts





}

