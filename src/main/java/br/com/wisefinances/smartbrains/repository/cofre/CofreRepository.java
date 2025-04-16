package br.com.wisefinances.smartbrains.repository.cofre;

import br.com.wisefinances.smartbrains.model.entity.cofre.Cofre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CofreRepository extends JpaRepository<Cofre, Integer> {

}