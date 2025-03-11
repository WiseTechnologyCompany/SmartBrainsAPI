package br.com.wisefinances.smartbrains.domain.abstracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractSpecificService<T, S, M> {

    public abstract Page<T> findAll(Pageable pPageable);

    public abstract T findById(Integer pId);

    public abstract M save(S Object);

    public abstract S update(Integer pId, S Object);

    public abstract M delete(Integer pId);

}