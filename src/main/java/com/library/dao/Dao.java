package com.library.dao;

import java.util.List;

public interface Dao<T> {
    List<T> findAll();

    T findById(Integer id);

    boolean delete(Integer id);

    boolean delete(T t);

    boolean update(T t);

    T create(T t);
}
