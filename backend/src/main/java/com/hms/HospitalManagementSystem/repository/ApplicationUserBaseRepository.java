package com.hms.HospitalManagementSystem.repository;

import com.hms.HospitalManagementSystem.model.ApplicationUserBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserBaseRepository extends GenericRepository<ApplicationUserBase, String> {
}
