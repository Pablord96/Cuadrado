package model

import android.graphics.Color

class CuadradoBordes(color: Int, ancho: Int, alto: Int, var colorBorde: Int) : Cuadrado(color, ancho, alto) {

    fun ponerBordeNegro(){
        colorBorde = Color.BLACK
    }
}