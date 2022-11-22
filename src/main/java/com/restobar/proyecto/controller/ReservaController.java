package com.restobar.proyecto.controller;

import com.restobar.proyecto.modelo.Mesa;
import com.restobar.proyecto.modelo.Reserva;
import com.restobar.proyecto.service.MesaService;
import com.restobar.proyecto.service.ReservaService;
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
import java.util.List;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private MesaService mesaService;

    @Autowired
    private ReservaService reservaService;

    //Reservas

    @GetMapping("/lista")
    public String listarReservas(Model model) {
        try {
            model.addAttribute("reservaList", reservaService.listAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "reserva/lista";
    }

    @GetMapping("/nuevo")
    public String nuevaReserva(Model model) {
        List<Mesa> mesaList = mesaService.listAll();
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("mesaList", mesaList);
        return "reserva/reservar";
    }

    @PostMapping("/guardar")
    public String guardarReserva(@Valid Reserva reserva, BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()){
            model.addAttribute("reserva", reserva);
            return "reserva/reservar";
        }
        reservaService.save(reserva);
        flash.addFlashAttribute("msgExito", "Regsitro Exitoso");
        return "redirect:/reserva/nuevo";
    }

    @GetMapping("/editar/{id}")
    public String editarReserva(@PathVariable(value = "id") Long id, Model model){
        List<Mesa> mesaList = mesaService.listAll();
        Reserva reserva = reservaService.FindById(id);
        model.addAttribute("reserva", reserva);
        model.addAttribute("mesaList", mesaList);
        return "reserva/reservar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarReserva(@PathVariable(value = "id") Long id, Model model){
        try {
            reservaService.DeleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/reserva/lista";
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
        flash.addFlashAttribute("msgExito", "Regsitro Exitoso");
        return "redirect:/reserva/mesas";
    }

    @GetMapping("/mesas/editar/{id}")
    public String editarMesa(@PathVariable(value = "id") Long id, Model model) {
        Mesa mesa = mesaService.FindById(id);
        model.addAttribute("mesa", mesa);

        return "reserva/mesas_form";
    }

    @GetMapping("/mesas/eliminar/{id}")
    public String eliminarMesa(@PathVariable(value = "id") Long id, Model model) {
        try {
            mesaService.DeleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/reserva/mesas";
    }

}
