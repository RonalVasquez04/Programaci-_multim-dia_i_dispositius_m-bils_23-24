package es.rfvl.mypreferences

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager

class LoginActivity : AppCompatActivity(), LoginFragment.FragmentLoginListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val nombre = prefs.getBoolean("sync", false);

        if (nombre){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            setContentView(R.layout.activity_login)

        }



    }

    override fun onLoginClicked() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}