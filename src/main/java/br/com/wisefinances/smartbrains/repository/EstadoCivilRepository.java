package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.model.entity.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {

}