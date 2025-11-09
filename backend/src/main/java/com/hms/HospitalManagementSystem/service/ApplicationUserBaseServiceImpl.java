package com.hms.HospitalManagementSystem.service;

import com.hms.HospitalManagementSystem.model.ApplicationUserBase;
import com.hms.HospitalManagementSystem.repository.ApplicationUserBaseRepository;
import com.hms.HospitalManagementSystem.repository.GenericRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserBaseServiceImpl extends genericServiceImpl<ApplicationUserBase,String>{

    @Autowired
    private ApplicationUserBaseRepository repository;

    private final Logger logger = LoggerFactory.getLogger(ApplicationUserBaseServiceImpl.class);

    @Override
    protected GenericRepository<ApplicationUserBase, String> getRepository() {
        return repository;
    }

}
