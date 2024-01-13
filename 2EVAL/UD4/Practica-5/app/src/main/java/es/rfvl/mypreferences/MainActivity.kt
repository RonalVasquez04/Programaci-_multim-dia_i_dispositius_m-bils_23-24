package es.rfvl.mypreferences

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import es.rfvl.mypreferences.databinding.ActivityMainBinding
import es.rfvl.mypreferences.databinding.NavHeaderBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingNav: NavHeaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingNav = NavHeaderBinding.inflate(layoutInflater)

        val prefs = getSharedPreferences("PreferenciasApp", Context.MODE_PRIVATE);
        val nombre = prefs.getString("nombre","");

        val navHeaderView = binding.navigationView.getHeaderView(0)

        val tvNameUsuario = navHeaderView.findViewById<TextView>(R.id.tvNameUsuario)

        tvNameUsuario.text = nombre

        bindingNav.tvNameUsuario.text = "RONALDO"

        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)
        setUpNavigationDrawer()

    }



    private fun setUpNavigationDrawer(){
        val toogle = ActionBarDrawerToggle(this,binding.drawerLayout
            , binding.myToolbar, R.string.navig_open, R.string.navig_cloese
        )
        binding.drawerLayout.addDrawerListener(toogle)

        toogle.syncState()
        //binding.navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId){
        R.id.action_favorite ->{

            Toast.makeText(this, "Information saved correctly",
                Toast.LENGTH_SHORT).show()
            true

        }
        R.id.action_settings -> {

            val nuevoFragmento = SettingsFragment()
            this.supportFragmentManager.beginTransaction()
                .replace(R.id.myfragmentcontainerview, nuevoFragmento)
                .addToBackStack(null)
                .commit()


            true
        }
        else ->  false
    }
}