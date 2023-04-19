package com.ead.authuser.specification;

import com.ead.authuser.entity.UserEntity;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {

    @And({
            @Spec(path = "userType", spec = Equal.class),
            @Spec(path = "UserStatus", spec = Equal.class),
            @Spec(path = "email", spec = Like.class)})
    public interface UserSpec extends Specification<UserEntity> {
    }
}
