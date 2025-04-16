package br.com.wisefinances.smartbrains.repository.usuario;

import br.com.wisefinances.smartbrains.model.entity.usuario.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {

    Usuarios findByEmail(String email);

}