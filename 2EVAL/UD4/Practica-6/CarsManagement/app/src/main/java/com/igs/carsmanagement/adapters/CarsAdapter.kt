package com.igs.carsmanagement.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.igs.carsmanagement.R
import com.igs.carsmanagement.classes.Car

class CarsAdapter (private val context: Context,
                   private val products: MutableList<Car>,
                   private val mListener: (Car) -> Unit) :
    RecyclerView.Adapter<CarsAdapter.ProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.car_item, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]
        holder.bindItem(product)
        holder.itemView.setOnClickListener { mListener(product) }
    }


    class ProductsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvBrand: TextView = view.findViewById(R.id.tvBrand)
        private val tvModel: TextView = view.findViewById(R.id.tvModel)

        fun bindItem(c: Car){
            tvBrand.text = c.brand
            tvModel.text = c.model
        }
    }
}