package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.model.entity.SituacaoCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoCadastroRepository extends JpaRepository<SituacaoCadastro, Integer> {

}