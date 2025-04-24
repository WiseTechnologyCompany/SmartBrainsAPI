package br.com.wisefinances.smartbrains.repository.movimentacao;

import br.com.wisefinances.smartbrains.model.entity.movimentacao.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

    @Query("SELECT m FROM Movimentacao m WHERE m.usuario.id = :idUsuario")
    List<Movimentacao> findAllUserTransactionsByUserId(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.usuario.id = :idUsuario AND m.tipoMovimentacao.id = 1")
    BigDecimal sumTotalEntrada(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.usuario.id = :idUsuario AND m.tipoMovimentacao.id = 2")
    BigDecimal sumTotalGastosFixos(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.usuario.id = :idUsuario AND m.tipoMovimentacao.id = 3")
    BigDecimal sumTotalDespesas(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.usuario.id = :idUsuario AND m.tipoMovimentacao.id = 1 AND m.dataCriacao BETWEEN :dataInicio AND :dataFim")
    BigDecimal sumTotalEntradaByDate(@Param("idUsuario") Integer idUsuario, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    @Query("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.usuario.id = :idUsuario AND m.tipoMovimentacao.id = 2 AND m.dataCriacao BETWEEN :dataInicio AND :dataFim")
    BigDecimal sumTotalGastosFixosByDate(@Param("idUsuario") Integer idUsuario, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    @Query("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.usuario.id = :idUsuario AND m.tipoMovimentacao.id = 3 AND m.dataCriacao BETWEEN :dataInicio AND :dataFim")
    BigDecimal sumTotalDespesasByDate(@Param("idUsuario") Integer idUsuario, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

}