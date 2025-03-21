package br.com.wisefinances.smartbrains.repository.create;

import br.com.wisefinances.smartbrains.model.create.entity.CreateMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository responsável por realizar as operações de criação e atualização de Movimentação.
 */
@Repository
public interface CreateMovimentacaoRepository extends JpaRepository<CreateMovimentacao, Integer>{

}
