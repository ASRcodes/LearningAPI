package com.example.learningapis

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningapis.databinding.RecyclerViewBinding
import com.squareup.picasso.Picasso

class AdapterRecycler(val context: Activity,val productArrayList:List<Product>)
    :RecyclerView.Adapter<AdapterRecycler.MyViewHolder>(){

        inner class MyViewHolder(var binding: RecyclerViewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerViewBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.binding.productName.setText(currentItem.title)
        Picasso.get().load(currentItem.thumbnail).into(holder.binding.productIMG)
        if (currentItem.rating.toInt() ==3){
            holder.binding.productRate.setText("* * *")
        }
        if (currentItem.rating.toInt()==4){
            holder.binding.productRate.setText("* * * *")
        }
        if (currentItem.rating.toInt()==5){
            holder.binding.productRate.setText("* * * * *")
        }
    }
}