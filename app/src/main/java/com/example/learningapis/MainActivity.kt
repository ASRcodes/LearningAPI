package com.example.learningapis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningapis.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    binding.floatingButton.setOnClickListener {
        startActivity(Intent(this@MainActivity,learnApi2::class.java))
    }

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
//        Now to fetch data with method on got the data and on failure
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
//                fetched data successfully then do this
                var responseBody = response.body()
                val productFetch =responseBody?.products!!

                val adapter = AdapterRecycler(this@MainActivity,productFetch)
                binding.apiRecycler.adapter = adapter
                binding.apiRecycler.layoutManager = LinearLayoutManager(this@MainActivity)

            }
            override fun onFailure(call: Call<MyData?>, t: Throwable) {
//          Not got the data
                Log.d("MainActivity","onFailure"+t.message)
            }
        })
    }
}