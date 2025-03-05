package br.com.smartbrains.domain.abstracts;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public abstract class AbstractService<T, M> {

    public abstract List<T> findAll();

    public abstract T findById(Integer pId);

    public abstract T save(T pObject);

    public abstract T update(Integer pId, T pObject);

    public abstract M delete(Integer pId);

}