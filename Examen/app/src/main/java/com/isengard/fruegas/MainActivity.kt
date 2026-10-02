package com.isengard.fruegas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //Foco en el edit text
        val identificador = findViewById<EditText>(R.id.editTextText)
        val error = findViewById<TextView>(R.id.error)
        val boton = findViewById<ImageButton>(R.id.imageButton)

        identificador.requestFocus()

        identificador.setOnFocusChangeListener { view, hasFocus  ->
            if (!tieneFoco && identificador.text.toString().trim().isEmpty()) {
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
                Toast.makeText(
                    this,
                    "¡Unidad $idTroppa enviada al Abismo de Helm!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        //Error
        identificador.setOnFocusChangeListener { view, hasFocus  ->
            if (!tieneFoco && identificador.text.toString().trim().isEmpty()) {
                identificador.error = "El ejército no acepta soldados anónimos"
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}