package br.com.wisefinances.smartbrains.repository.tipomovimentacao;

import br.com.wisefinances.smartbrains.model.entity.tipomovimentacao.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMovimentacaoRepository extends JpaRepository<TipoMovimentacao, Integer> {

}