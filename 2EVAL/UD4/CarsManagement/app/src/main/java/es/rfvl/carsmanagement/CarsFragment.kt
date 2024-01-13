package es.rfvl.carsmanagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import es.rfvl.carsmanagement.database.AppDB
import es.rfvl.carsmanagement.database.Auto
import es.rfvl.carsmanagement.databinding.FragmentCarsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CarsFragment : Fragment(){

    private lateinit var binding: FragmentCarsBinding
    private var cars = mutableListOf<Car>()
    private var lastIndex: Int = 0
    private lateinit var carsAdapter: CarsAdapter
    private lateinit var database: AppDB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentCarsBinding.inflate(inflater, container, false)

        database= Room.databaseBuilder(requireContext(),
            AppDB::class.java,
            "CarsDB").build()

        binding.btnInsert.setOnClickListener {
            if(!binding.etBrand.text.isNullOrBlank() && !binding.etModel.text.isNullOrBlank()){
                //val c = Car(++lastIndex, binding.etBrand.text.toString(), binding.etModel.text.toString())

                val car = Auto(brand = binding.etBrand.text.toString(), model = binding.etModel.text.toString())
                lifecycleScope.launch(Dispatchers.IO) {
                    database.carDao().insert(car)
                    withContext(Dispatchers.Main){
                        Toast.makeText(context, "AutoInsertado", Toast
                            .LENGTH_SHORT).show()
                    }

                }

                //cars.add(c)
                cleanCar()
                loadCarsFromDatabase()
            }else{
                Toast.makeText(requireContext(), getString(R.string.warn_car_empty), Toast.LENGTH_SHORT).show()
            }
        }

        setRecyclerView()
        loadCarsFromDatabase()

        return binding.root
    }

    private fun setRecyclerView(){
        //cars.addAll(
        //    listOf<Car>(
        //        Car(1, "Seat", "León"),
        //        Car(2, "Audi", "Q7"),
        //        Car(3, "Skoda", "Karoq")
        //    )
        //)

        //lastIndex = cars.size


        val carClickFunction = { car: Car ->
            lifecycleScope.launch(Dispatchers.IO) {
                val auto = Auto(id = car.id, brand = car.brand, model = car.model)
                database.carDao().delete(auto)
            }
            cars.remove(car)
            cleanCar()
            carsAdapter.notifyDataSetChanged()
        }

        carsAdapter = CarsAdapter(requireContext(), cars, carClickFunction)

        binding.rvCars.adapter = carsAdapter
        binding.rvCars.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
    }


    private fun cleanCar(){
        carsAdapter.notifyDataSetChanged()
        binding.etBrand.setText("")
        binding.etModel.setText("")
    }

    private fun loadCarsFromDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            val carsDatabase = database.carDao().getAll()
            withContext(Dispatchers.Main) {
                cars.clear()
                cars.addAll(carsDatabase.map { Car(it.id, it.brand, it.model) })
                carsAdapter.notifyDataSetChanged()
            }
        }
    }
}