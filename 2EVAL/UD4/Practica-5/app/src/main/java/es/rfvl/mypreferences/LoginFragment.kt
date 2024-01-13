package es.rfvl.mypreferences

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import es.rfvl.mypreferences.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var mListener: FragmentLoginListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        var registrerClick: Boolean = false
        val user = binding.usuarioIntroducido
        val password = binding.contraseAIntroducida





        binding.botonLogin.setOnClickListener{
            if (user.text.toString().isEmpty() || password.text.toString().isEmpty()){
                Snackbar.make(binding.root, "Debe rellenar el usuario y la contraseña", Snackbar.LENGTH_SHORT).show()

            }else{
                val prefs = requireActivity().getSharedPreferences("PreferenciasApp", Context.MODE_PRIVATE);
                val nombre = prefs.getString("nombre","");
                with(prefs.edit()){
                    putString("nombre",user.text.toString())
                    putBoolean("sync",false)
                    apply()
                }
                mListener.onLoginClicked()
            }
        }
        return binding.root
    }

    interface FragmentLoginListener{
        fun onLoginClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentLoginListener){
            mListener = context
        }else{
            throw Exception("EEROR")
        }
    }






}