package api.acessos.novo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


public class UserDto {

    private Long Id;

    private String nome;

    private String username;

    private String email;

    private String senha;

    private Boolean ativo;

    private Long perfil;

    public Boolean isAtivo() {
        return this.ativo;
    }

    public Boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPerfil() {
        return perfil;
    }

    public void setPerfil(Long perfil) {
        this.perfil = perfil;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(Id, userDto.Id) && Objects.equals(nome, userDto.nome)
                && Objects.equals(username, userDto.username) && Objects.equals(email, userDto.email)
                && Objects.equals(senha, userDto.senha) && Objects.equals(ativo, userDto.ativo)
                && Objects.equals(perfil, userDto.perfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nome, username, email, senha, ativo, perfil);
    }


}