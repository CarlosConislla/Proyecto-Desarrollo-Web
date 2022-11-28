package com.restobar.proyecto.controller;

import com.restobar.proyecto.modelo.DetalleVenta;
import com.restobar.proyecto.modelo.Producto;
import com.restobar.proyecto.modelo.Venta;
import com.restobar.proyecto.service.DetalleVentaService;
import com.restobar.proyecto.service.ProductoService;
import com.restobar.proyecto.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/detalleVenta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private VentaService ventaService;

    Venta venta = new Venta();

    List<DetalleVenta> listaDetalles = new ArrayList<DetalleVenta>();

    @GetMapping("/buscarPlatillo")
    public String mostrarDatoDelPlato(Producto producto, Model modelo){
        if(!productoService.existsById(producto.getId())){
            modelo.addAttribute("listaDetalles", listaDetalles);
            modelo.addAttribute("total",total);
            modelo.addAttribute("error","platillo no encontrado");
            return "/venta/detalleVenta";
        }
        Producto producto1 = productoService.getOne(producto.getId()).get();
        modelo.addAttribute("listaDetalles", listaDetalles);
        modelo.addAttribute("total",total);
        modelo.addAttribute("producto", producto1);
        return "/venta/detalleVenta";
    }

    @GetMapping("/buscarPlatilloNombre")
    public String mostrarDatoDelPlatoNombre(Producto producto, Model modelo){
        if(!productoService.existsByNombre(producto.getNombre())){
            modelo.addAttribute("listaDetalles", listaDetalles);
            modelo.addAttribute("total",total);
            modelo.addAttribute("error","platillo no encontrado");
            return "/venta/detalleVenta";
        }
        Producto producto1 = productoService.findByNombre(producto.getNombre()).get();
        modelo.addAttribute("listaDetalles", listaDetalles);
        modelo.addAttribute("total",total);
        modelo.addAttribute("producto", producto1);
        return "/venta/detalleVenta";
    }

    Integer item=0;
    Double total;

    @PostMapping("/agregar")
    public ModelAndView agregarATabla(@RequestParam Integer id, @RequestParam Integer cantidad, Model modelo, Producto producto){
        Producto productoObtenido =  productoService.getOne(id).get();
        DetalleVenta detalleVenta = new DetalleVenta();
        total = 0.0;

        item = item +1;
        detalleVenta.setId(item);
        detalleVenta.setCantidad(cantidad);
        detalleVenta.setPrecio(producto.getPrecio());
        detalleVenta.setProducto(productoObtenido);
        detalleVenta.setImporte(producto.getPrecio()*cantidad);
        listaDetalles.add(detalleVenta);//añadiendo una detalle de venta a la lista

        for (int i =0; i<listaDetalles.size(); i++){
            total= total+listaDetalles.get(i).getImporte();
        }

        modelo.addAttribute("listaDetalles", listaDetalles);
        modelo.addAttribute("total",total);
        return  new ModelAndView("/venta/detalleVenta");
    }

    Venta venta1 = new Venta();
    @PostMapping("/procesar")
    public ModelAndView procesarVenta(){

        //Agregación final de la venta a la base de datos
        Integer id = ventaService.findById(); //obtenemos el id de la venta ingresada, la ultima
        venta.setId(id);
        //venta.setTotal(total);
        venta1 = ventaService.getOne(id).get();
        venta1.setTotal(total);
        ventaService.save(venta1);
        //mandando la lista de detalles a la tabla
        for (DetalleVenta detalleVenta: listaDetalles){
            detalleVenta.setVenta(venta);
            detalleVentaService.save(detalleVenta);
        }

        //Actualización del Stock de bebidas
        for (int i=0; i<listaDetalles.size(); i++){
            Producto productoUpdate=listaDetalles.get(i).getProducto();
            int idProductoUpdate=listaDetalles.get(i).getProducto().getId();

            int cantidad = listaDetalles.get(i).getCantidad();
            int aux = listaDetalles.get(i).getProducto().getCantidad();

            int cantidadUpdate = aux-cantidad;
            //productoUpdate.setId(idProductoUpdate);
            productoUpdate.setCantidad(cantidadUpdate);
            productoService.save(productoUpdate);
        }
        venta = new Venta();
        venta1 = new Venta();
        listaDetalles.clear();
        return new ModelAndView("/venta/ventasGenerales");
    }
}
