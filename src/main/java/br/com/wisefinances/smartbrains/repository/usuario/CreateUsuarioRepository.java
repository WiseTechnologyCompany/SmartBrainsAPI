package br.com.wisefinances.smartbrains.repository.usuario;

import br.com.wisefinances.smartbrains.model.entity.usuario.CreateUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository responsável por realizar as operações de criação e atualização de um Usuário.
 */
@Repository
public interface CreateUsuarioRepository extends JpaRepository<CreateUsuario, Integer> {

}