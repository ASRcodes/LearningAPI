package com.example.learningapis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningapis.databinding.ActivityLearnApi2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class learnApi2 : AppCompatActivity() {
    private lateinit var binding: ActivityLearnApi2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnApi2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface2::class.java)
        val retroData = retrofitBuilder.getInfo()
        retroData.enqueue(object : Callback<MySecData?>
        {
            override fun onResponse(call: Call<MySecData?>, response: Response<MySecData?>) {
                val responseBody =response.body()
                var fetch = responseBody?.recipes?.toMutableList()?: mutableListOf()
                val adapter = AdapterRecycler2(this@learnApi2,fetch)
                binding.recyclerFood.adapter = adapter
                binding.recyclerFood.layoutManager = LinearLayoutManager(this@learnApi2)
            }
            override fun onFailure(call: Call<MySecData?>, t: Throwable) {
//          Now got the data
                Log.d("MainActivity","onFailure"+t.message)
            }
          }
        )
    }
}











