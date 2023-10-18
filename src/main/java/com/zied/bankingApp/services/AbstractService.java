package com.zied.bankingApp.services;

import java.util.List;

public interface AbstractService <T> {

    Integer save(T dto);

    List<T> findAll();

    T findById(Integer id);

    void delete(Integer id);
}
