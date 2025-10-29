package model

import android.graphics.Color

class CuadradoBordes(color: Int, ancho: Int, alto: Int, var colorBorde: Int = Color.BLACK) : Cuadrado(color, ancho, alto) {

    fun cambiarColorBorde(nuevoColorBorde: Int){
        colorBorde = nuevoColorBorde
    }
}