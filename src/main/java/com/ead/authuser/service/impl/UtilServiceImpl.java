package com.ead.authuser.service.impl;

import com.ead.authuser.service.UtilService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilServiceImpl implements UtilService {

    @Value("${ead.api.uri.course}")
    String REQUEST_URI;

    @Override
    public String createUrlGetAllCoursesByUser(UUID userId, Pageable pageable) {

        return REQUEST_URI + "/courses?userId=" + userId + "&page=" + pageable.getPageNumber()
                + "&size=" + pageable.getPageSize()
                //  + "&sort=" + pageable.getSort().toString().replaceAll(":", ",")
                ;
    }
}
