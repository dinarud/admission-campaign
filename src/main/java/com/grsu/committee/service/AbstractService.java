package com.grsu.committee.service;

import com.grsu.committee.entities.AbstractModel;

import java.util.List;

public interface AbstractService<E extends AbstractModel> {
    void save(E entity);

    void update(E entity);

    E get(Long id);

    List<E> getAll();

    void delete(Long id);

    void saveOrUpdate(E entity);
}
