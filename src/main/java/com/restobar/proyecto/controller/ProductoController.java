package com.restobar.proyecto.controller;

import com.restobar.proyecto.modelo.Categoria;
import com.restobar.proyecto.modelo.Producto;
import com.restobar.proyecto.service.CategoriaService;
import com.restobar.proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    //lista los productos en la tabla//ESTA LISTO
    @GetMapping("lista")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/producto/lista");
        List<Producto> productos = productoService.list();
        mv.addObject("productos", productos);
        return mv;
    }

    //manda a la pagina para crear productos//ESTALISTO
    @GetMapping("nuevo")
    public String nuevo(Model modelo) {
        Producto producto = new Producto();
        modelo.addAttribute("producto", producto);
        List<Categoria> listaCategorias = categoriaService.list();
        modelo.addAttribute("listaCategoria", listaCategorias);
        return "/producto/nuevo";
    }

    @PostMapping("/guardar")
    public ModelAndView crearProducto(Producto producto, Model modelo) {
        ModelAndView mv = new ModelAndView();
        if (productoService.existsByNombre(producto.getNombre())) {
            mv.setViewName("producto/nuevo");
            List<Categoria> listaCategorias = categoriaService.list();
            modelo.addAttribute("listaCategoria", listaCategorias);
            mv.addObject("error", "el producto ya existe");
            return mv;
        }
        productoService.save(producto);
        mv.setViewName("redirect:/producto/lista");
        return mv;
    }

    //manda a la pagina para editar y completa los campos//ESTA LISTO
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id, Model modelo) {
        List<Categoria> listaCategorias = categoriaService.list();
        modelo.addAttribute("listaCategoria", listaCategorias);
        Producto producto = productoService.getOne(id).get();
        ModelAndView mv = new ModelAndView("/producto/editar");
        mv.addObject("producto", producto);
        return mv;
    }

    @PostMapping("/actualizar")
    public ModelAndView actualizar(@RequestParam Integer id, Producto producto, Model modelo) {
        ModelAndView mv = new ModelAndView();
        Producto producto1 = productoService.getOne(id).get();
        if (productoService.existsByNombre(producto.getNombre()) && productoService.getByNombre(producto.getNombre()).get().getId() != id) {
            mv.setViewName("producto/editar");
            List<Categoria> listaCategorias = categoriaService.list();
            modelo.addAttribute("listaCategoria", listaCategorias);
            mv.addObject("error", "el producto ya existe");
            return mv;
        }
        producto1.setNombre(producto.getNombre());
        producto1.setPrecio(producto.getPrecio());
        producto1.setCategoria(producto.getCategoria());
        producto1.setCantidad(producto.getCantidad());
        productoService.save(producto1);
        return new ModelAndView("redirect:/producto/lista");
    }

    @GetMapping("/borrar/{id}")
    public ModelAndView borrar(@PathVariable("id") Integer id) {
        productoService.delete(id);
        return new ModelAndView("redirect:/producto/lista");
    }
}
