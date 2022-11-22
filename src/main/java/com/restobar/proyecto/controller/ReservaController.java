package com.restobar.proyecto.controller;

import com.restobar.proyecto.modelo.Mesa;
import com.restobar.proyecto.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    MesaService mesaService;

    //Reservas

    @GetMapping("/lista")
    public String listarReservas() {

        return "reserva/lista";
    }

    @GetMapping("/nuevo")
    public String guardarReserva() {
        return "/reserva/reservar";
    }

    //Mesas

    @GetMapping("/mesas")
    public String listarMesas(Model model) {
        try {
            model.addAttribute("mesaList", mesaService.listAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "reserva/mesas";
    }

    @GetMapping("/mesas/nuevo")
    public String nuevaMesa(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "reserva/mesas_form";
    }

    @PostMapping("/mesas/guardar")
    public String guardarMesa(@Valid Mesa mesa, BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("mesa", mesa);
            return "reserva/mesas_form";
        }
        mesaService.save(mesa);
        flash.addFlashAttribute("msgExito","Regsitro Exitoso");
        return "redirect:/reserva/mesas";
    }

    @GetMapping("/mesas/editar/{id}")
    public String editarMesa(@PathVariable(value = "id") Long id, Model model){
        Mesa mesa = mesaService.FindById(id);
        model.addAttribute("mesa", mesa);

        return "reserva/mesas_form";
    }

    @GetMapping("/mesas/eliminar/{id}")
    public String eliminarMesa(@PathVariable(value = "id") Long id, Model model){
        try {
            mesaService.DeleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/reserva/mesas";
    }

}
