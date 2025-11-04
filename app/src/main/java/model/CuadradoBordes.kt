package model

import android.graphics.Color

class CuadradoBordes(color: Int, ancho: Int, alto: Int, var colorBorde: Int = Color.BLACK) : Cuadrado(color, ancho, alto) {

    //Ejemplo de clase nested
    class ManejoColor{
        //Definimos un objeto comun a toda la clase son los colores
        companion object{
            val ROJO = Color.RED
            val AZUL = Color.BLUE
            val VERDE = Color.GREEN
            val NEGRO = Color.BLACK

            fun obtenerCuatroColoresAleatorio(): Int{
                //Creamos una lista con los valores companion object
                val colores = listOf(ROJO,AZUL,VERDE,NEGRO)
                return colores.random()
            }
        }
    }

    fun cambiarColorBorde(nuevoColorBorde: Int){
        colorBorde = nuevoColorBorde
    }

}