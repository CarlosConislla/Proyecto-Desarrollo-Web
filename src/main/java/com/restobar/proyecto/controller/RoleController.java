package com.restobar.proyecto.controller;

import com.restobar.proyecto.modelo.Role;
import com.restobar.proyecto.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/lista")
    public String listarRoles(Model model){
        try {
            model.addAttribute("roleList", roleService.listAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "user/roles";
    }

    @GetMapping("/nuevo")
    public String nuevoRol(Model model){
        model.addAttribute("role", new Role());
        return "user/role_form";
    }

    @PostMapping("/guardar")
    public String guardarRol(Role role){
        roleService.save(role);
        return "redirect:/role/lista";
    }

    @GetMapping("/editar/{id}")
    public String editarRol(@PathVariable(value = "id") Long id, Model model){
        Role role = roleService.FindById(id);
        model.addAttribute("role", role);

        return "user/role_form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarRol(@PathVariable(value = "id") Long id, Model model){
        try {
            roleService.DeleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/role/lista";
    }
}
