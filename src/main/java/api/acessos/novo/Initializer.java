package api.acessos.novo;

import api.acessos.novo.entity.Role;
import api.acessos.novo.entity.Usuario;
import api.acessos.novo.service.RoleService;
import api.acessos.novo.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Initializer {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void criaUsuariosEPermissoes() {
//        Role roleAdmin = roleService.findById(1L);
//
//        Usuario user = new Usuario();
//        user.setAtivo(true);
//        user.setEmail("teste@teste.com");
//        user.setNome("juliano");
//        user.setSenha(new BCryptPasswordEncoder().encode("123456"));
//        user.setUsername("julianoosmirhein");
//        user.setRoles(roleAdmin);
//
//        userService.save(user);

    }
}