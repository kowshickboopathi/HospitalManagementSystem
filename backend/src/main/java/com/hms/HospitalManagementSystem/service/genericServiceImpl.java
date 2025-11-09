package com.hms.HospitalManagementSystem.service;

import com.hms.HospitalManagementSystem.model.PaginationResponse;
import com.hms.HospitalManagementSystem.model.baseEntity;
import com.hms.HospitalManagementSystem.repository.GenericRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public abstract class genericServiceImpl<T extends baseEntity, ID> implements GenericService<T, ID> {

    private final Logger logger = LoggerFactory.getLogger(genericServiceImpl.class);

    protected abstract GenericRepository<T, ID> getRepository();

    @Override
    public T create(T entity) {
        if(entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        onBeforeSaveDB(entity);
        return getRepository().save(entity);
    }

    @Override
    public T getById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public T update(T entity,String id) {
        logger.info("Updating entity with ID: {}", id);
        T existing = getRepository().findBySid(id);
        if (existing == null) {
            logger.warn("Entity not found for ID: {}", id);
            return null;
        }
        onBeforeUpdateDB(entity);
        logger.info("Entity found for ID: {}, proceeding with update", id);
        return getRepository().save(entity);
    }

    @Override
    public void onBeforeSaveDB(T entity) {
        logger.info("Entering onBeforeSaveDB called for ApplicationUserBase");
        entity.setSid();
        entity.setCreatedDate();
        entity.setModifiedDate();
        entity.setCreatedBy();
        entity.setModifiedBy();
        logger.info("Exiting onBeforeSaveDB completed for ApplicationUserBase");
    }

    @Override
    public void onBeforeUpdateDB(T entity) {
        logger.info("Entering onBeforeUpdateDB called for ApplicationUserBase");
        entity.setModifiedDate();
        entity.setModifiedBy();
        logger.info("Exiting onBeforeUpdateDB completed for ApplicationUserBase");
    }

    @Override
    public PaginationResponse<T> findAll(int page, int size) {
        logger.info("Fetching all entities - Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return new PaginationResponse<>(getRepository().findAll(pageable));
    }

    @Override
    public boolean delete(String sid) {
        logger.info("Attempting to delete entity with SID: {}", sid);
        T existingEntity = getRepository().findBySid(sid);
        if (existingEntity== null) {
            logger.warn("Entity not found for SID: {}", sid);
            return false;
        }
        getRepository().delete(existingEntity);
        logger.info("Successfully deleted entity with SID: {}", sid);
        return true;
    }

    @Override
    public T getBySid(String sid) {
        return getRepository().findBySid(sid);
    }
}
