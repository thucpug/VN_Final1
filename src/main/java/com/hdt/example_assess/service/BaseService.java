package com.hdt.example_assess.service;

import java.util.List;

public interface BaseService<T> {
    public T add(T obj);

    public T delete(T obj);

    public T delete(int id);

    public T update(T obj);

    public T save(T obj);

    public T findById(int id);

    public List<T> findAll();

    public int counts();
}
