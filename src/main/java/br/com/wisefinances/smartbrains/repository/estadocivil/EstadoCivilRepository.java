package br.com.wisefinances.smartbrains.repository.estadocivil;

import br.com.wisefinances.smartbrains.model.entity.estadocivil.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {

}