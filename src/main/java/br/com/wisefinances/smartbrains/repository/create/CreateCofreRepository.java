package br.com.wisefinances.smartbrains.repository.create;

import br.com.wisefinances.smartbrains.model.create.entity.CreateCofre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateCofreRepository extends JpaRepository<CreateCofre, Integer> {

}