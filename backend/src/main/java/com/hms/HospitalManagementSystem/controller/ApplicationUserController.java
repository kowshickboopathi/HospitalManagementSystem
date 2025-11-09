package com.hms.HospitalManagementSystem.controller;

import com.hms.HospitalManagementSystem.model.ApplicationUserBase;
import com.hms.HospitalManagementSystem.model.PaginationResponse;
import com.hms.HospitalManagementSystem.service.ApplicationUserBaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/applicationUser")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserBaseServiceImpl applicationUserBaseService;

    private final Logger logger = LoggerFactory.getLogger(ApplicationUserController.class);


    @PostMapping("/create")
    public ResponseEntity<?> createNewUser(@RequestBody ApplicationUserBase user) {
        try{
            if(applicationUserBaseService.getById(user.getEmail())==null){
                logger.info("Entering for creating new user");
                ApplicationUserBase savedUser = applicationUserBaseService.create(user);
                logger.info("Exiting after creating new user");
                return ResponseEntity.ok(savedUser);
            }
        }
        catch (Exception e){
            logger.error("Error occurred while creating new user: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists.");
    }

    @PutMapping("/update/{sid}")
    public ResponseEntity<?> updateUser(@RequestBody ApplicationUserBase user,@PathVariable String sid) {
        try {
            logger.info("Entering for updating user");
            ApplicationUserBase updatedUser = applicationUserBaseService.update(user, sid);
            if (updatedUser == null) {
                logger.warn("User not found for SID: {}", sid);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found for SID: " + sid);
            }
            logger.info("Exiting after updating new user");
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            logger.error("Error occurred while updating existing user: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public PaginationResponse<ApplicationUserBase> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Fetching all users - Page: {}, Size: {}", page, size);
        PaginationResponse<ApplicationUserBase> users = applicationUserBaseService.findAll(page, size);
        return users;
    }

    @DeleteMapping("/delete/{sid}")
    public ResponseEntity<?> deleteUser(@PathVariable String sid) {
        try {
            logger.info("Entering for deleting user with SID: {}", sid);
            boolean isDeleted = applicationUserBaseService.delete(sid);
            if (isDeleted) {
                logger.info("User deleted successfully with SID: {}", sid);
                return ResponseEntity.ok("User deleted successfully.");
            } else {
                logger.warn("User not found for SID: {}", sid);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found for SID: " + sid);
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting user: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{sid}")
    public ResponseEntity<?> getUserById(@PathVariable String sid) {
        try {
            logger.info("Fetching user with SID: {}", sid);
            ApplicationUserBase user = applicationUserBaseService.getBySid(sid);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                logger.warn("User not found for SID: {}", sid);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found for SID: " + sid);
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching user: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/ping")
    public String ping() {
        return "App is running!";
    }
}
