package com.restobar.proyecto.controller;

import com.restobar.proyecto.modelo.Categoria;
import com.restobar.proyecto.repository.CategoriaRepository;
import com.restobar.proyecto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/lista")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/categoria/lista");
        List<Categoria> categorias = categoriaService.list();
        mv.addObject("categorias", categorias);
        return mv;
    }

    @GetMapping("/nuevo")
    public String nuevo(){
        return "/categoria/nuevo";
    }

    @PostMapping("/guardar")
    public ModelAndView crear(@RequestParam String nombre){
        ModelAndView mv = new ModelAndView();

        if(categoriaService.existsByNombre(nombre)){
            mv.setViewName("categoria/nuevo");
            mv.addObject("error", "el nombre ya existe");
            return  mv;
        }
        //mandamos el objeto
        Categoria categoria = new Categoria(nombre);
        categoriaService.save(categoria);
        mv.setViewName("redirect:/categoria/lista");
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        if(!categoriaService.existsById(id)) //si no existe el id para editar, mandar a esta vista
            return new ModelAndView("redirect:/categoria/lista");
        Categoria categoria = categoriaService.getOne(id).get();
        ModelAndView mv = new ModelAndView("/categoria/editar"); //existe la categoria, mandamos a editar
        mv.addObject("categoria", categoria);
        return  mv;
    }

    @PostMapping("/actualizar")
    public ModelAndView actualizar(@RequestParam Integer id, @RequestParam String nombre){
        if(!categoriaService.existsById(id))
            return new ModelAndView("redirect:/categoria/lista");
        ModelAndView mv = new ModelAndView();
        Categoria categoria = categoriaService.getOne(id).get();

        //validamos si el nombre ya esta registrado
        if(categoriaService.existsByNombre(nombre) && categoriaService.getByNombre(nombre).get().getId() != id){
            mv.setViewName("categoria/editar");
            mv.addObject("error", "la categoria ya existe");
            mv.addObject("categoria", categoria);
            return mv;
        }

        categoria.setNombre(nombre);
        categoriaService.save(categoria);
        return new ModelAndView("redirect:/categoria/lista");
    }

    //elimina un registro y manda a la lista de
    @GetMapping("/borrar/{id}")
    public ModelAndView borrar(@PathVariable("id") Integer id){
        if(categoriaService.existsById(id)){
            categoriaService.delete(id);
            return  new ModelAndView("redirect:/categoria/lista");
        }
        return null;
    }
}
