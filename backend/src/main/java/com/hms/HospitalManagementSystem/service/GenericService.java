package com.hms.HospitalManagementSystem.service;

import com.hms.HospitalManagementSystem.model.PaginationResponse;

public interface GenericService<T, ID> {
    T create(T entity);
    T getById(ID id);
    void onBeforeSaveDB(T entity);
    void onBeforeUpdateDB(T entity);
    T update(T entity,String id);
    PaginationResponse<T> findAll(int page, int size);
    boolean delete(String sid);
    T getBySid(String sid);
}
