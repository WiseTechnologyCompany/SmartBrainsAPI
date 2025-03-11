package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.model.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

}