package com.example.TiendaInstrumentosSpring.repository

import com.example.TiendaInstrumentosSpring.model.Instrumento
import org.springframework.stereotype.Repository
import java.io.File

@Repository
class InstrumentoFileRepository {

    private val filePath = "src/main/resources/data/instrumentos.csv"

    fun findAll(): MutableList<Instrumento> = File(filePath).readLines().map { linea ->
        val partes = linea.split(";")
        Instrumento(
            id_instrumento = partes[0].toInt(),
            nombre = partes[1],
            fabricante = partes[2],
            anoFacbricacion = partes[3].toInt(),
            precio = partes[4].toDouble()
        )
    }.toMutableList()

    fun save(instrumento: Instrumento) {
        val instrumentos = findAll()
        val index = instrumentos.indexOfFirst { it.id_instrumento == instrumento.id_instrumento }

        if (index != -1) {
            instrumentos[index] = instrumento
        } else {
            val nuevoId = (instrumentos.maxOfOrNull { it.id_instrumento } ?: 0) + 1
            instrumentos.add(instrumento.copy(id_instrumento = nuevoId))
        }
        escribirArchivo(instrumentos)
    }

    fun deleteById(id_instrumento: Int) {
        val instrumentos = findAll()
        instrumentos.removeIf { it.id_instrumento == id_instrumento }
        escribirArchivo(instrumentos)
    }

    private fun escribirArchivo(instrumentos: List<Instrumento>) {
        val contenido = instrumentos.joinToString(separator = "\n") { instrumento ->
            "${instrumento.id_instrumento};${instrumento.nombre};${instrumento.fabricante};${instrumento.anoFacbricacion};${instrumento.precio}"
        }
        File(filePath).writeText(contenido)
    }
}