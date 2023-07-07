package com.ead.authuser.repository;

import com.ead.authuser.model.UserCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserCourseRepository  extends JpaRepository<UserCourseModel, UUID>, JpaSpecificationExecutor<UserCourseModel> {
}
