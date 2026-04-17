package com.fidel.simbasokogaren

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class Payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Receive/Retrieve Extras Data the product_name and product_cost

        val productname=intent.getStringExtra("product_name")
        val productcost=intent.getIntExtra("product_cost",0)
        val productphoto=intent.getStringExtra("product_photo")
        val productdescription=intent.getStringExtra("product_description")

        val image=findViewById<ImageView>(R.id.product_photo)
        val cost=findViewById<TextView>(R.id.product_cost)
        val name=findViewById<TextView>(R.id.product_name)
        val description=findViewById<TextView>(R.id.product_description)
        val phone=findViewById<EditText>(R.id.phone)
        val pay=findViewById<Button>(R.id.pay)


        cost.text="Ksh $productcost"
        name.text=productname
        description.text=productdescription

        Glide.with(this)
            .load(productphoto)
            .circleCrop()
            .into(image)

        pay.setOnClickListener {
//            set Api Endpoint
            val api = "http://fidel.alwaysdata.net/api/mpesa_payment"
//            create data using RequestParams, put phone and cost as key value pairs
            val data= RequestParams()
            data.put("amount",productcost)
            data.put("Phone",phone.text.toString())

//            Access API helper
            val helper=ApiHelper(applicationContext)
//            Post data to api endpoint
            helper.post(api,data)

        }



    }
}