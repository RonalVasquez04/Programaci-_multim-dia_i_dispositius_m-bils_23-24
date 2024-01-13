package es.rfvl.dogs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.rfvl.dogs.R
import es.rfvl.dogs.classes.Dog


class DogsAdapter(private val dogs: MutableList<String>) : RecyclerView.Adapter<DogsAdapter.DogsViewFolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewFolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return DogsViewFolder(view)
    }

    override fun onBindViewHolder(holder: DogsViewFolder, position: Int) {
        val dog = dogs[position]
        holder.bindItem(dog)
    }

    override fun getItemCount(): Int {
        return dogs.size
    }

    class DogsViewFolder(view: View): RecyclerView.ViewHolder(view){
        private val photo: ImageView = view.findViewById(R.id.imagenDog)

        fun bindItem(i:String){
            Picasso.get().load(i).into(photo)
        }

    }


}