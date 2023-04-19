package com.ead.authuser.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GeneralMessage {
    public final String USER_NOT_FOUND = "User Not Found";
    public final String USER_DELETED_SUCCESS = "User Deleted";
    public final String UPDATE_PASSWORD_SUCCESS = "Password Updated Successfully";
    public final String UPDATE_PASSWORD_ERROR = "Mismatched Password";
    public final String UPDATE_PASSWORD_ERROR_WITH_SAME_VALUES = "Password Can't Be The Same";
}
