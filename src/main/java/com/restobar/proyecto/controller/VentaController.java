package com.restobar.proyecto.controller;

import com.restobar.proyecto.modelo.Producto;
import com.restobar.proyecto.modelo.Venta;
import com.restobar.proyecto.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;


    @PostMapping("/guardarVenta")
    public ModelAndView registrarVenta(Venta venta){
        ModelAndView view = new ModelAndView();
        ventaService.save(venta);
        view.setViewName("redirect:/venta/venta");
        return view;
    }

    @GetMapping("/hacerVenta")
    public String hacerVenta(Model modelo){
        Venta venta = new Venta();
        modelo.addAttribute("venta", venta);
        return "/venta/venta";
    }

    @GetMapping("/detalleVenta")
    public String hacerDetalleVenta(Model modelo){
        Producto producto = new Producto();
        modelo.addAttribute("producto", producto);
        return "/venta/detalleVenta";
    }

    @GetMapping("/lista")
    public ModelAndView listarVentas(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/venta/ventasGenerales");
        List<Venta> ventas = ventaService.list();
        mv.addObject("ventas", ventas);
        return mv;
    }
}
