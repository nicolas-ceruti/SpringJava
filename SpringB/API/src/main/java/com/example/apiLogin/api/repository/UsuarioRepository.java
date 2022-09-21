package com.example.apiLogin.api.repository;

import com.example.apiLogin.api.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuarioAndSenha(String usuario, String senha);

    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> findById(String id);

}
