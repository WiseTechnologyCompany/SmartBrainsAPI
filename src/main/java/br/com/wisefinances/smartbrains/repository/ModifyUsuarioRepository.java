package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.model.modify.entity.ModifyUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository responsável por realizar as operações de criação e atualização de um Usuário.
 */
@Repository
public interface ModifyUsuarioRepository extends JpaRepository<ModifyUsuario, Integer> {

}