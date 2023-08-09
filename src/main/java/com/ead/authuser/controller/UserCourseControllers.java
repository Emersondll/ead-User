package com.ead.authuser.controller;

import com.ead.authuser.client.CourseClient;
import com.ead.authuser.dto.CourseDto;
import com.ead.authuser.dto.UserCourseDto;
import com.ead.authuser.model.UserCourseModel;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.service.UserCourseService;
import com.ead.authuser.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@Log4j2
public class UserCourseControllers {

    @Autowired
    CourseClient courseClient;

    @Autowired
    private UserService service;

    @Autowired
    private UserCourseService userCourseService;

    public static final String USER_NOT_FOUND = "User Not Found";

    @GetMapping("/user/{userId}/courses")
    public ResponseEntity<Page<CourseDto>> getAllCoursesByUser(@PathVariable(value = "userId") UUID userId,
                                                               @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC)
                                                               Pageable pageable) {
        log.info("getAllCoursesByUser");
        return ResponseEntity.status(HttpStatus.OK).body(courseClient.getAllCoursesByUser(userId, pageable));
    }

    @PostMapping("/user/{userId}/courses/subscription")
    public ResponseEntity<Object> saveSubscriptionUserInCourse(@PathVariable(value = "userId") UUID userId, @RequestBody @Valid UserCourseDto userCourseDto) {
        log.info("getCourseById");

        Optional<UserModel> userModelOptional = service.findById(userId);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }

        if (userCourseService.existsByUserAndCourseId(userModelOptional.get(), userCourseDto.getCourseId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already registered in this course");
        }

        UserCourseModel userCourseModel = userCourseService.save(userModelOptional.get().convertToUserCourseModel(userCourseDto.getCourseId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(userCourseModel);
    }
}
