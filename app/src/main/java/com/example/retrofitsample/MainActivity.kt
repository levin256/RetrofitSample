package com.example.retrofitsample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

val api = Retrofit.Builder()
    .baseUrl("https://eflvndkyg3.execute-api.ap-northeast-1.amazonaws.com/")
    .addConverterFactory(ScalarsConverterFactory.create())
    .build().create(SampleApiService::class.java)

class MainActivity : AppCompatActivity() {

    // 実行ボタン
    lateinit var executeButton: Button

    // APIから取得した文字を表示するTextView
    lateinit var apiTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiTextView = findViewById(R.id.api_string)
        executeButton = findViewById(R.id.execute);
        executeButton.setOnClickListener {
            // API呼出を行う
            lifecycleScope.launch {
                val response = callApi()
                apiTextView.text = response
            }
            Toast.makeText(applicationContext, "execute!", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun callApi(): String? {
        return try {
            api.get()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}