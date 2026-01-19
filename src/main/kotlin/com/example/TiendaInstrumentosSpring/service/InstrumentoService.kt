package com.example.TiendaInstrumentosSpring.service

import com.example.TiendaInstrumentosSpring.model.Instrumento
import com.example.TiendaInstrumentosSpring.repository.InstrumentoFileRepository
import org.springframework.stereotype.Service

@Service
class InstrumentoService(
    private val repository: InstrumentoFileRepository
) {

    fun listarInstrumentos(): MutableList<Instrumento> = repository.findAll()

    fun buscarPorId(id_instrumento: Int): Instrumento? =
        repository.findAll().find { it.id_instrumento == id_instrumento }

    fun guardar(instrumento: Instrumento) {
        repository.save(instrumento)
    }

    fun borrar(id_instrumento: Int) {
        repository.deleteById(id_instrumento)
    }
}