package br.com.wisefinances.smartbrains.repository.codigo;

import br.com.wisefinances.smartbrains.model.entity.codigo.CodigoVerificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CodigoVerificacaoRepository extends JpaRepository<CodigoVerificacao, Integer> {

    @Query("SELECT c FROM CodigoVerificacao c WHERE c.email = :pEmail ORDER BY c.id DESC")
    List<CodigoVerificacao> checkVerificationEmailAndCode(@Param("pEmail") String pEmail);

}