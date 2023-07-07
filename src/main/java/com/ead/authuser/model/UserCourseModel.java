package com.ead.authuser.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USER_COURSES")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserCourseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserModel user;
    @Column(nullable = false)
    private UUID courseId;
}
