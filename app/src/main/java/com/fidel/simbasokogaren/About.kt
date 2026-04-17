package com.fidel.simbasokogaren

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class About : AppCompatActivity() {
//    Declare a tts variable.Lateinit is used to write a variable that will be initialised later

    lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val text=findViewById<TextView>(R.id.textview)

        val inputText=findViewById<EditText>(R.id.inputText)

        val speak=findViewById<Button>(R.id.speakButton)

//        Initialize text to speech engine

        tts= TextToSpeech(this){
//        check if the speech is successful
        if (it== TextToSpeech.SUCCESS){
            tts.language= Locale.US
        }
        }
        speak.setOnClickListener {
            val textt=inputText.text.toString()
            tts.speak(textt, TextToSpeech.QUEUE_FLUSH,null,null)

        }
    }

//    Stop the tts from speaking when app is closed/destroyed/killed
    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()

    }


}