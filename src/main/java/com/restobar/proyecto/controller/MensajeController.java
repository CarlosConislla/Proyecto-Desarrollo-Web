package com.restobar.proyecto.controller;

import com.restobar.proyecto.modelo.Mensaje;
import com.restobar.proyecto.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mensaje")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;


    @PostMapping("/save")
    public String save(@RequestParam String nombre, @RequestParam String email, @RequestParam String contenido){
        Mensaje mensajeSave = new Mensaje();
        mensajeSave.setNombre(nombre);
        mensajeSave.setEmail(email);
        mensajeSave.setContenido(contenido);
        mensajeService.save(mensajeSave);
        return "index";
    }

    @GetMapping("/lista")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/mensaje/lista");
        List<Mensaje> listaMensajes = mensajeService.list();
        mv.addObject("listaMensajes", listaMensajes);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id, Model modelo){
        Mensaje mensaje = mensajeService.getOne(id).get();
        ModelAndView mv = new ModelAndView("/mensaje/editar");
        mv.addObject("mensaje", mensaje);
        return mv;
    }

    @PostMapping("/actualizar")
    public ModelAndView update(@RequestParam Integer id,Mensaje mensaje,Model modelo ){
        Mensaje mensaje1 = mensajeService.getOne(id).get();
        mensaje1.setNombre(mensaje.getNombre());
        mensaje1.setEmail(mensaje.getEmail());
        mensaje1.setContenido(mensaje.getContenido());
        mensaje1.setEstado(mensaje.getEstado());
        mensajeService.save(mensaje1);
        return new ModelAndView("redirect:/mensaje/lista");
    }

    @GetMapping("/borrar/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        mensajeService.delete(id);
        return new ModelAndView("redirect:/mensaje/lista");
    }
}
