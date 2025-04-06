package br.com.wisefinances.smartbrains.infra.security.repository;

import br.com.wisefinances.smartbrains.infra.security.model.entity.Autenticacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AutenticacaoRepository extends JpaRepository<Autenticacao, Integer> {

    Boolean existsByEmail(String email);

    UserDetails findByEmail(String email);

}