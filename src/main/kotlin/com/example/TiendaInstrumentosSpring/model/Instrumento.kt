package com.example.TiendaInstrumentosSpring.model

data class Instrumento(
    var id_instrumento: Int,
    var nombre: String,
    var fabricante: String,
    var anoFacbricacion: Int,
    var precio: Double
)