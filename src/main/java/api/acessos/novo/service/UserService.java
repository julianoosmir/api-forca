package api.acessos.novo.service;


import api.acessos.novo.config.jwt.JwtRequest;
import api.acessos.novo.config.jwt.JwtTokenUtil;
import api.acessos.novo.dto.UserDto;
import api.acessos.novo.dto.UserResponseDto;
import api.acessos.novo.entity.Role;
import api.acessos.novo.entity.Usuario;
import api.acessos.novo.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Usuario save(Usuario user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public Usuario salvar(UserDto userdto) {
        userdto.setSenha(this.enconderPassword(userdto.getSenha()));
        Usuario user = this.mapper.map(userdto, Usuario.class);
        Role role = roleService.findById(userdto.getPerfil());
        user.setRoles(role);
        return repository.save(user);
    }

    public Usuario findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public List<UserResponseDto> findAllDtos() {
        List<UserResponseDto> userdtos = new ArrayList<>();
        this.findAll().forEach(user -> {
            UserResponseDto dto = new UserResponseDto(user.getId(), user.getNome(), user.getUsername(),
                    user.getRoles().getName());
            userdtos.add(dto);
        });
        return userdtos;
    };

    // private String getNamesRoles(List<Role> roles) {
    //     List<String> nomesRoles = roles.stream().map(Role::getName).collect(Collectors.toList());
    //     return nomesRoles.stream().map(Object::toString)
    //             .collect(Collectors.joining(", "));
    // }

    private String enconderPassword(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }



    private String getRoleName(Long id) {
        Role role = findById(id).getRoles();
        return role.getName();
    }



    public ResponseEntity<?> signin(JwtRequest authenticationRequest) {

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getSenha());

            final Usuario userDetails = repository.findByUsername(authenticationRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);

            // final AuthDto authDto = new AuthDto(getRoleName(userDetails.getId()), true, token);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.getMessage());
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USUARIO DESABILITADO", e);
        } catch (BadCredentialsException e) {
            throw new Exception("CREDENCIAIS INVALIDAS", e);
        } catch (InternalAuthenticationServiceException e) {
            throw new Exception("USUARIO NÃO CADASTRADO", e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}