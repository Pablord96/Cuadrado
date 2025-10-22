package com.example.cuadrado

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import model.Cuadrado

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Indentificacion de la cista
        val cuadradoView : View = findViewById<View>(R.id.cuadrado)

        /*usamos metodo post para que se ejecute este bloque de cogido en el hilo de la interfaz de usuario justo después de que se cargue la vista
        se construya y se mida
        */

        cuadradoView.post{
            //Varaibles que recogen los datos de la vista inicial
            val inicialAncho = cuadradoView.width
            val inicialAlto = cuadradoView.height
            val inicialX : Int = cuadradoView.x.toInt()
            val inicialY : Int =  cuadradoView.y.toInt()

            //Asociar la vista con el objeto cuadrado
            //ContextCompact es una clase para aceder a recursos
            val cuadrado : Cuadrado = Cuadrado(ContextCompat.getColor(this, R.color.red), inicialAncho, inicialAlto).apply{
                x = inicialX
                y = inicialY
            }

            var buttonArriba : Button = findViewById<Button>(R.id.buttonArriba)
            var buttonAbajo : Button = findViewById<Button>(R.id.buttonAbajo)
            var buttonDerecha : Button = findViewById<Button>(R.id.buttonDerecha)
            var buttonIzquierda : Button = findViewById<Button>(R.id.buttonIzquierda)
            var buttonCambiarTamanio : Button = findViewById<Button>(R.id.buttonCambiarTamanio)
            var buttonCambiarColor : Button = findViewById<Button>(R.id.buttonCambiarColor)

            //Ponemos botones a la escucha
            buttonArriba.setOnClickListener{
                cuadrado.moverArriba()
                actualizarVista(cuadrado, cuadradoView)
            }
            buttonAbajo.setOnClickListener{
                cuadrado.moverAbajo()
                actualizarVista(cuadrado, cuadradoView)
            }

            buttonDerecha.setOnClickListener {
                cuadrado.moverDerecha()
                actualizarVista(cuadrado, cuadradoView)
            }

            buttonIzquierda.setOnClickListener{
                cuadrado.moverIzquierda()
                actualizarVista(cuadrado, cuadradoView)
            }

            buttonCambiarTamanio.setOnClickListener {
                cuadrado.cambiarTamanio(150,150)
                actualizarVista(cuadrado, cuadradoView)
            }

            buttonCambiarColor.setOnClickListener{
                //cuadrado.color = ContextCompat.getColor(this, R.color.blue)
                cuadrado.color = generarColorAleatorio()
                actualizarVista(cuadrado, cuadradoView)
            }
        } //post
    }

    fun generarColorAleatorio () : Int {
        val rojo = (0..255).random()
        val verde = (0..255).random()
        val azul = (0..255).random()

        //Color es la clase para almacenar colores y el metod rgb saca el numero
        return android.graphics.Color.rgb(rojo, verde, azul)
    }

    private fun actualizarVista (cuadrado:Cuadrado, cuadradoView:View){

        //Aqui es donde enlazamos la vista con el objeto
        //La vista actulizara su ancho y su alto con los datos del objeto
        cuadradoView.layoutParams.width = cuadrado.ancho
        cuadradoView.layoutParams.height = cuadrado.alto

        //Cambiamos el color
        cuadradoView.setBackgroundColor(cuadrado.color)

        //Actulizamos las coordenadas
        cuadradoView.x = cuadrado.x.toFloat()
        cuadradoView.y = cuadrado.y.toFloat()

        //Ejecutar los cambios
        cuadradoView.requestLayout()
    }
}

