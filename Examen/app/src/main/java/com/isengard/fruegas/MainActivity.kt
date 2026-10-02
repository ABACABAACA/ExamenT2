package com.isengard.fruegas

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Log.d("CICLO_VIDA", "onCreate()")

        val identificador = findViewById<EditText>(R.id.editTextText)
        val error = findViewById<TextView>(R.id.error)
        val boton = findViewById<ImageButton>(R.id.imageButton)
        val antorcha = findViewById<CheckBox>(R.id.checkBox)

        if (savedInstanceState != null) {

            identificador.setText(
                savedInstanceState.getString("ID_TROPPA", "")
            )

            antorcha.isChecked =
                savedInstanceState.getBoolean("ANTORCHA", false)

            if (savedInstanceState.getBoolean("ERROR_VISIBLE", false)) {
                error.text = "El ejército no acepta soldados anónimos"
                error.visibility = TextView.VISIBLE
            }
        }

        identificador.requestFocus()

        identificador.setOnFocusChangeListener { view, hasFocus  ->
            if (!hasFocus && identificador.text.toString().trim().isEmpty()) {
                identificador.error = "El ejército no acepta soldados anónimos"
            }
        }

        boton.setOnClickListener {

            val idTroppa = identificador.text.toString().trim()

            if (idTroppa.isEmpty()) {
                error.text = "El ejército no acepta soldados anónimos"
                error.visibility = TextView.VISIBLE
                identificador.requestFocus()
            } else {
                if (!antorcha.isChecked) {
                    Log.e(
                        "FraguasIsengard",
                        "¡Peligro! Unidad enviada sin fuego"
                    )
                }
                Toast.makeText(
                    this,
                    "¡Unidad $idTroppa enviada al Abismo de Helm!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        //Error
        identificador.setOnFocusChangeListener { view, hasFocus  ->
            if (!hasFocus && identificador.text.toString().trim().isEmpty()) {
                identificador.error = "El ejército no acepta soldados anónimos"
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val identificador = findViewById<EditText>(R.id.editTextText)
        val antorcha = findViewById<CheckBox>(R.id.checkBox)
        val error = findViewById<TextView>(R.id.error)

        outState.putString(
            "ID_TROPPA",
            identificador.text.toString()
        )

        outState.putBoolean(
            "ANTORCHA",
            antorcha.isChecked
        )

        outState.putBoolean(
            "ERROR_VISIBLE",
            error.visibility == TextView.VISIBLE
        )
    }

    override fun onStart() {
        super.onStart()
        Log.d("FraguasIsengard", "onStart: Las fraguas se encienden")
    }
    override fun onResume() {
        super.onResume()
        Log.d("FraguasIsengard", "onResume: Los Uruk-hai vuelven a la batalla")
    }
    override fun onPause() {
        super.onPause()
        Log.d("FraguasIsengard", "onPause: Saruman detiene la produccion temporalmente")
    }
    override fun onStop() {
        super.onStop()
        Log.d("FraguasIsengard", "onStop: Las fraguas de Isengard quedan en silencio")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("FraguasIsengard", "onDestroy: Isengard apaga sus fraguas definitivamente")
    }
}