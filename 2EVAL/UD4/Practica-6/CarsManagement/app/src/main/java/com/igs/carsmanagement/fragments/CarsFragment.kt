package com.igs.carsmanagement.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.igs.carsmanagement.R
import com.igs.carsmanagement.adapters.CarsAdapter
import com.igs.carsmanagement.classes.Car
import com.igs.carsmanagement.databinding.FragmentCarsBinding

class CarsFragment : Fragment(){

    private lateinit var binding: FragmentCarsBinding
    private var cars = mutableListOf<Car>()
    private var lastIndex: Int = 0
    private lateinit var carsAdapter: CarsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentCarsBinding.inflate(inflater, container, false)

        binding.btnInsert.setOnClickListener {
            if(!binding.etBrand.text.isNullOrBlank() && !binding.etModel.text.isNullOrBlank()){
                val c = Car(++lastIndex, binding.etBrand.text.toString(), binding.etModel.text.toString())
                cars.add(c)
                cleanCar()
            }else{
                Toast.makeText(requireContext(), getString(R.string.warn_car_empty), Toast.LENGTH_SHORT).show()
            }
        }

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView(){
        cars.addAll(
            listOf<Car>(
                Car(1, "Seat", "León"),
                Car(2, "Audi", "Q7"),
                Car(3, "Skoda", "Karoq")
            )
        )

        lastIndex = cars.size


        val carClickFunction = { car: Car ->
            cars.remove(car)
            cleanCar()
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
}