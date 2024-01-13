package es.rfvl.mypreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val usernamePreference = findPreference<EditTextPreference>("signature")
        val prefs = requireActivity().getSharedPreferences("PreferenciasApp", Context.MODE_PRIVATE)
        val nombre = prefs.getString("nombre", "")

        val defaultValue = nombre
        usernamePreference?.text = defaultValue
    }

    override fun onStart() {
        super.onStart()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onStop() {
        super.onStop()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "nombre") {
            val usernamePreference = findPreference<EditTextPreference>("signature")
            val nuevoNombre = sharedPreferences?.getString("nombre", "")
            usernamePreference?.text = nuevoNombre
        }
    }
}
