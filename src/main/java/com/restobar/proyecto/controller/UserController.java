package com.restobar.proyecto.controller;

import com.restobar.proyecto.modelo.Role;
import com.restobar.proyecto.modelo.Usuario;
import com.restobar.proyecto.service.RoleService;
import com.restobar.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("lista")
    public String listarUsuarios(Model model) {
        model.addAttribute("userList", userService.listAll());
        return "user/usuarios";
    }

    @GetMapping("/nuevo")
    public String nuevoUser(Model model) {
        List<Role> roleList = roleService.listAll();
        model.addAttribute("user", new Usuario());
        model.addAttribute("roleList", roleList);
        return "user/user_form";
    }

    @PostMapping("/guardar")
    public String guardarUser(Usuario usuario) {
        userService.save(usuario);
        return "redirect:/user/lista";
    }

    @GetMapping("/editar/{id}")
    private String editarUser(@PathVariable(value = "id") Integer id, Model model){
        List<Role> roleList = roleService.listAll();
        model.addAttribute("roleList", roleList);

        Usuario user = userService.FindById(id);
        model.addAttribute("user", user);

        return "user/user_form";
    }

    @GetMapping("/eliminar/{id}")
    private String eliminarUser(@PathVariable(value = "id") Integer id, Model model){
        userService.DeleteById(id);
        return "redirect:/user/lista";
    }
}
