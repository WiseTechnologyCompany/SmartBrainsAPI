package br.com.wisefinances.smartbrains.repository.movimentacao;

import br.com.wisefinances.smartbrains.model.entity.movimentacao.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    @Query("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.usuario.id = :idUsuario AND m.tipoMovimentacao.id = 1 AND EXTRACT(YEAR FROM m.dataCriacao) = 2025")
    BigDecimal findTotalEntradaPorAno(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.usuario.id = :idUsuario AND m.tipoMovimentacao.id IN (2, 3) AND EXTRACT(YEAR FROM m.dataCriacao) = 2025")
    BigDecimal findTotalSaidaPorAno(@Param("idUsuario") Integer idUsuario);

    @Query(value = """
            SELECT
                TO_CHAR(data_criacao, 'YYYY-MM') AS mes,
                SUM(valor) AS totalEntrada
            FROM movimentacao
            WHERE id_usuario = :idUsuario
            AND id_tipomovimentacao = 1
            AND EXTRACT(YEAR FROM data_criacao) = 2025
            GROUP BY TO_CHAR(data_criacao, 'YYYY-MM')
            ORDER BY mes
            """, nativeQuery = true)
    List<Object[]> findTotalEntradaPorMes(@Param("idUsuario") Integer idUsuario);

    @Query(value = """
            SELECT
                TO_CHAR(data_criacao, 'YYYY-MM') AS mes,
                SUM(valor) AS totalSaida
            FROM movimentacao
            WHERE id_usuario = :idUsuario
            AND id_tipomovimentacao IN (2, 3)
            AND EXTRACT(YEAR FROM data_criacao) = 2025
            GROUP BY TO_CHAR(data_criacao, 'YYYY-MM')
            ORDER BY mes
            """, nativeQuery = true)
    List<Object[]> findTotalSaidaPorMes(@Param("idUsuario") Integer idUsuario);

    @Query("""
            SELECT
                m.tipoCategoria.id,
                m.tipoCategoria.descricao,
                SUM(m.valor)
            FROM Movimentacao m
            WHERE m.usuario.id = :idUsuario
            AND m.tipoCategoria.id IN (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            GROUP BY m.tipoCategoria.id, m.tipoCategoria.descricao
            ORDER BY m.tipoCategoria.id
            """)
    List<Object[]> findTotalCategorias(@Param("idUsuario") Integer idUsuario);

}