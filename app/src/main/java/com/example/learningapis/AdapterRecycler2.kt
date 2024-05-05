package com.example.learningapis

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningapis.databinding.Recycler2apiBinding
import com.squareup.picasso.Picasso

class AdapterRecycler2(val context: Context, val recipeList: MutableList<Recipe>):RecyclerView.Adapter<AdapterRecycler2.ViewHolder>() {
    inner class ViewHolder(var binding:Recycler2apiBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=Recycler2apiBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return  recipeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val foodItem = recipeList[position]
//        holder.binding.id.setText(context.getText(foodItem.id))
        holder.binding.name.text=(foodItem.name)
        val yo = foodItem.id.toString()
        holder.binding.id.text = yo

        // Bind instructions
//        We have made a String using the StringBuilder
        val instructionsText = StringBuilder()
//        Then we are fetching the instruction with the index and hence it's an array so we'll use for loop for that
        for ((index, instruction) in foodItem.instructions.withIndex()) {
//            Here we'll append the fetched value with index and hence index starts with 0 therefore we'll start it with 0+1(i.e, index+1)
            instructionsText.append("${index + 1}. $instruction\n").trimEnd()
        }
        holder.binding.instruction.text = instructionsText.toString()

//        // Bind ingredients
//        val ingredientsText = StringBuilder()
//        for (ingredient in recipe.ingredients) {
//            ingredientsText.append("- $ingredient\n")
//        }
//        holder.binding.ingredients.text = ingredientsText.toString().trimEnd()

        val ingredients = StringBuilder()
        for (ingredient in foodItem.ingredients){
            ingredients.append("- $ingredient\n")
        }
        holder.binding.ingredient.text = ingredients.toString()

        holder.binding.onClick.setOnClickListener {
            val intent = Intent(context,Show::class.java)
            intent.putExtra("INS",instructionsText.toString())
            intent.putExtra("Ingre",ingredients.toString())
            context.startActivity(intent)
        }
        Picasso.get().load(foodItem.image).into(holder.binding.foodIMG)
        if (foodItem.rating.toInt() ==3){
            holder.binding.rate.setText("* * *")
        }
        if (foodItem.rating.toInt()==4){
            holder.binding.rate.setText("* * * *")
        }
        if (foodItem.rating.toInt()==5){
            holder.binding.rate.setText("* * * * *")
        }

    }
    fun deleteItem(position: Int){
        recipeList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,recipeList.size)
    }

}
