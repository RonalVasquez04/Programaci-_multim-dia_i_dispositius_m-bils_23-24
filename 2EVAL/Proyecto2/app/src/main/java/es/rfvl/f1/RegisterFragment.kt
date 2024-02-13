package es.rfvl.f1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import es.rfvl.f1.databinding.FragmentRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(layoutInflater)
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        binding.btnRegistrarseNewUser.setOnClickListener {
            val email = binding.textEmailNew.text.toString()
            val pass = binding.textPasswordNew.text.toString()
            val passConfirmation = binding.textPasswordConfirmation.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && passConfirmation.isNotEmpty()){
                if(!email.isNullOrBlank() && !pass.isNullOrBlank() ){
                    lifecycleScope.launch {
                        if (pass.length >= 6){
                            if (passConfirmation == pass){
                                val userCreated = AuthManager().createUser(email,pass)
                                withContext(Dispatchers.Main){
                                    if (userCreated != null){
                                        Toast.makeText(requireContext(),"Usuario Creado", Toast.LENGTH_LONG).show()
                                        fragmentManager?.popBackStack()
                                    }else{
                                        Toast.makeText(requireContext(),"NO CREADO", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }else{
                                Toast.makeText(requireContext(),"Las contraseñas no coincide", Toast.LENGTH_LONG).show()
                            }
                        }else{
                            Snackbar.make(binding.root, "La contraseña debe ser de 6 caracteres", Snackbar.LENGTH_SHORT).show()
                        }

                    }
                }
            }else{
                Snackbar.make(binding.root, "Debe rellenar el usuario y la contraseña", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.btnGoBack.setOnClickListener {
            fragmentManager?.popBackStack()
        }



        return binding.root
    }
}