package br.com.wisefinances.smartbrains.repository.codigo;

import br.com.wisefinances.smartbrains.model.entity.codigo.CodigoVerificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoVerificacaoRepository extends JpaRepository<CodigoVerificacao, Integer> {

}