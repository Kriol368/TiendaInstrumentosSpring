package com.example.TiendaInstrumentosSpring.controller

import com.example.TiendaInstrumentosSpring.model.Instrumento
import com.example.TiendaInstrumentosSpring.service.InstrumentoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class InstrumentoController(
    private val instrumentoService: InstrumentoService
) {

    @GetMapping("/instrumentos")
    fun listar(model: Model): String {
        model.addAttribute("instrumentos", instrumentoService.listarInstrumentos())
        return "instrumentos"
    }

    @GetMapping("/instrumento/{id_instrumento}")
    fun detalle(@PathVariable id_instrumento: Int, model: Model): String {
        val instrumento = instrumentoService.buscarPorId(id_instrumento) ?: return "errorInstrumento"
        model.addAttribute("instrumento", instrumento)
        return "detalleInstrumento"
    }

    @GetMapping("/instrumentos/nuevo")
    fun nuevoInstrumento(model: Model): String {
        val instrumentoVacio = Instrumento(0, "", "", 0, 0.0)
        model.addAttribute("instrumento", instrumentoVacio)
        model.addAttribute("titulo", "Nuevo Instrumento")
        return "formularioInstrumento"
    }

    @GetMapping("/instrumentos/editar/{id_instrumento}")
    fun editarInstrumento(@PathVariable id_instrumento: Int, model: Model): String {
        val instrumento = instrumentoService.buscarPorId(id_instrumento) ?: return "redirect:/instrumentos"
        model.addAttribute("instrumento", instrumento)
        model.addAttribute("titulo", "Editar Instrumento")
        return "formularioInstrumento"
    }

    @PostMapping("/instrumentos/guardar")
    fun guardarInstrumento(@ModelAttribute instrumento: Instrumento): String {
        instrumentoService.guardar(instrumento)
        return "redirect:/instrumentos"
    }

    @GetMapping("/instrumentos/borrar/{id_instrumento}")
    fun borrarInstrumento(@PathVariable id_instrumento: Int): String {
        instrumentoService.borrar(id_instrumento)
        return "redirect:/instrumentos"
    }
}