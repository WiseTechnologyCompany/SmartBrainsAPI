package br.com.wisefinances.smartbrains.repository.movimentacao;

import br.com.wisefinances.smartbrains.model.entity.movimentacao.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

    @Query("SELECT m FROM Movimentacao m WHERE m.usuario.id = :idUsuario")
    List<Movimentacao> findAllUserTransactionsByUserId(@Param("idUsuario") Integer idUsuario);

}