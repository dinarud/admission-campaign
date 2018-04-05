package com.grsu.committee.service.impl;

import com.grsu.committee.dataaccess.AbstractDao;
import com.grsu.committee.entities.AbstractModel;
import com.grsu.committee.service.AbstractService;
import com.grsu.committee.table.AbstractTable;

import java.util.List;

public abstract class AbstractServiceImpl<T extends AbstractTable<E>, E extends AbstractModel> implements AbstractService<E> {

    protected AbstractDao<T, E> dao;

    @Override
    public void save(E entity) {
        dao.save(entity);
    }

    @Override
    public void update(E entity) {
        dao.update(entity);
    }

    @Override
    public E get(Long id) {
        return dao.get(id);
    }

    @Override
    public List<E> getAll() {
        return dao.getAll();
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public void saveOrUpdate(E entity) {
        dao.saveOrUpdate(entity);
    }
}
