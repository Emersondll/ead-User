package com.ead.authuser.specification;

import com.ead.authuser.model.UserCourseModel;
import com.ead.authuser.model.UserModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.UUID;

public class SpecificationTemplate {

    public static Specification<UserModel> userCourseId(UUID courseId) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Join<UserModel, UserCourseModel> userCourseModelJoin = root.join("userCourses");
            return criteriaBuilder.equal(userCourseModelJoin.get("courseId"), courseId);
        };
    }

    @And({
            @Spec(path = "userType", spec = Equal.class),
            @Spec(path = "UserStatus", spec = Equal.class),
            @Spec(path = "fullName", spec = Equal.class),
            @Spec(path = "email", spec = Like.class)})
    public interface UserSpec extends Specification<UserModel> {
    }
}
