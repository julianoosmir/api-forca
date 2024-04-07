package api.acessos.novo.controller;

import api.acessos.novo.dto.UserDto;
import api.acessos.novo.dto.UserResponseDto;
import api.acessos.novo.entity.Usuario;
import api.acessos.novo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Usuario> testGet() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Usuario testGetById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @GetMapping("/todos")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<UserResponseDto> dtoGet() {
        return this.userService.findAllDtos();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Usuario userPost(@RequestBody UserDto userdto) {
        return this.userService.salvar(userdto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Usuario userPut(@RequestBody UserDto user) {
        return this.userService.salvar(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void userDelete(@PathVariable Long id) {
        this.userService.delete(id);
    }

}
