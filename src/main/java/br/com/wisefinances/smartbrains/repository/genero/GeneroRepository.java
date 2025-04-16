package br.com.wisefinances.smartbrains.repository.genero;

import br.com.wisefinances.smartbrains.model.entity.genero.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

}