package com.ead.authuser.service.impl;

import com.ead.authuser.service.UtilService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilServiceImpl implements UtilService {

    String REQUEST_URI_COURSE = "http://localhost:8082";

    @Override
    public String createUrlGetAllCoursesByUser(UUID userId, Pageable pageable) {

        return REQUEST_URI_COURSE + "/courses?userId=" + userId + "&page=" + pageable.getPageNumber()
                + "&size=" + pageable.getPageSize()
                //  + "&sort=" + pageable.getSort().toString().replaceAll(":", ",")
                ;
    }
}
