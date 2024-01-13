package es.rfvl.dogs

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import es.rfvl.dogs.adapter.DogsAdapter
import es.rfvl.dogs.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private  lateinit var mAdapter: DogsAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var listDogs: MutableList<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        binding.btnSearch.setOnClickListener{
            searchDog(binding.textBreed.text.toString())
        }

    }

    private fun setUpRecyclerView(){
        listDogs = emptyList<String>().toMutableList()
        mAdapter = DogsAdapter(listDogs)
        binding.myRecyclerView.layoutManager = GridLayoutManager(this,2)
        binding.myRecyclerView.adapter = mAdapter


    }

    private fun searchDog(breed: String){
        if (!breed.isNullOrEmpty()){
            lifecycleScope.launch(Dispatchers.IO){
                val call = RetrofitObject.getInstance().create(DogsService::class.java).getDogs(breed)
                val response = call.body()

                withContext(Dispatchers.Main){
                    if (response?.status == "success"){
                        updateDogs(response.images)
                    }else{
                        println("MyTag Response: $response")
                        Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT).show()
                    }
                }


            }


        }else{
            Toast.makeText(this@MainActivity,"Error, You must type a breed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateDogs(images: List<String>?) {
        images?.let {
            listDogs.clear()
            listDogs.addAll(it)
            mAdapter.notifyDataSetChanged()
        }
    }


}