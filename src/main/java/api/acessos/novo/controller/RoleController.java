package api.acessos.novo.controller;

import api.acessos.novo.entity.Role;
import api.acessos.novo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAll() {
        return roleService.findAll();
    }

    @PostMapping
    public Role saveRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }

}