package com.example.cuadrado

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import model.CuadradoBordes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Indentificacion de la cista
        val cuadradoView : View = findViewById<View>(R.id.cuadrado)

        /*usamos metodo post para que se ejecute este bloque de cogido en el hilo de la interfaz de usuario justo después de que se cargue
        la vista se construya y se mida
        */

        cuadradoView.post{
            //Varaibles que recogen los datos de la vista inicial
            val inicialAncho = cuadradoView.width
            val inicialAlto = cuadradoView.height
            val inicialX : Int = cuadradoView.x.toInt()
            val inicialY : Int =  cuadradoView.y.toInt()

            //Asociar la vista con el objeto cuadrado
            //ContextCompact es una clase para aceder a recursos
            /*val cuadrado : Cuadrado = Cuadrado(ContextCompat.getColor(this, R.color.red), inicialAncho, inicialAlto).apply{
                x = inicialX
                y = inicialY
            }*/

            /*val cuadrado : CuadradoBordes = CuadradoBordes(
                ContextCompat.getColor(this, R.color.red),
                inicialAncho,
                inicialAlto
            ).apply{
                x = inicialX
                y = inicialY
                bordeColor = ContextCompat.getColor(this@MainActivity, R.color.black)
            }*/

            val cuadrado : CuadradoBordes = CuadradoBordes(
                ContextCompat.getColor(this, R.color.red),
                inicialAncho,
                inicialAlto,
                Color.BLACK // Color de borde inicial
            ).apply{
                x = inicialX
                y = inicialY
            }

            var buttonArriba : Button = findViewById<Button>(R.id.buttonArriba)
            var buttonAbajo : Button = findViewById<Button>(R.id.buttonAbajo)
            var buttonDerecha : Button = findViewById<Button>(R.id.buttonDerecha)
            var buttonIzquierda : Button = findViewById<Button>(R.id.buttonIzquierda)
            var buttonAumentarTamanio : Button = findViewById<Button>(R.id.buttonAumentarTamanio)
            var buttonDisminuirtamanio : Button = findViewById<Button>(R.id.buttonDisminuirTamanio)
            var buttonCambiarColor : Button = findViewById<Button>(R.id.buttonCambiarColor)
            var buttonCambiarColorBorde : Button = findViewById<Button>(R.id.buttonCambiarColorBorde)


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

            buttonAumentarTamanio.setOnClickListener {
                //cuadrado.cambiarTamanio(150,150)

                // 1. Obtenemos el tamaño actual desde el objeto 'cuadrado'
                val anchoActual = cuadrado.ancho
                val altoActual = cuadrado.alto

                // 2. Calculamos el nuevo tamaño sumando el incremento
                val nuevoAncho = anchoActual + 10
                val nuevoAlto = altoActual + 10

                // 3. Establecemos el nuevo tamaño
                cuadrado.cambiarTamanio(nuevoAncho, nuevoAlto)
                actualizarVista(cuadrado, cuadradoView)
            }

            buttonDisminuirtamanio.setOnClickListener {
                //cuadrado.cambiarTamanio(150,150)

                // 1. Obtenemos el tamaño actual desde el objeto 'cuadrado'
                val anchoActual = cuadrado.ancho
                val altoActual = cuadrado.alto

                // 2. Calculamos el nuevo tamaño sumando el incremento
                val nuevoAncho = anchoActual - 10
                val nuevoAlto = altoActual - 10

                // 3. Establecemos el nuevo tamaño
                cuadrado.cambiarTamanio(nuevoAncho, nuevoAlto)
                actualizarVista(cuadrado, cuadradoView)
            }

            buttonCambiarColor.setOnClickListener{
                //cuadrado.color = ContextCompat.getColor(this, R.color.blue)
                cuadrado.color = generarColorAleatorio()
                actualizarVista(cuadrado, cuadradoView)
            }

            buttonCambiarColorBorde.setOnClickListener{
                cuadrado.colorBorde = generarColorAleatorio()
                cuadrado.ponerBordeNegro()
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

    private fun actualizarVista (cuadrado:CuadradoBordes, cuadradoView:View){

        //Aqui es donde enlazamos la vista con el objeto
        //La vista actulizara su ancho y su alto con los datos del objeto
        cuadradoView.layoutParams.width = cuadrado.ancho
        cuadradoView.layoutParams.height = cuadrado.alto

        // Creamos un drawable para el borde
        val borderDrawable = GradientDrawable()
        borderDrawable.setColor(cuadrado.color) // Color de fondo
        borderDrawable.setStroke(10, cuadrado.colorBorde) // Ancho y color del borde

        cuadradoView.background = borderDrawable // Usamos 'background' en lugar de 'setBackgroundColor'


        //Actulizamos las coordenadas
        cuadradoView.x = cuadrado.x.toFloat()
        cuadradoView.y = cuadrado.y.toFloat()

        //Ejecutar los cambios
        cuadradoView.requestLayout()
    }
}

