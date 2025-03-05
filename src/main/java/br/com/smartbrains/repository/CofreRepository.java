package br.com.smartbrains.repository;

import br.com.smartbrains.model.entity.Cofre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CofreRepository extends JpaRepository<Cofre, Integer> {

}