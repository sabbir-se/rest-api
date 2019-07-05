package rest.util;

import org.springframework.http.HttpStatus;

public class Constant {

    static final String MESSAGE = "message";

    public static final String CREATE_MESSAGE = "Create Successfully.";
    public static final String UPDATE_MESSAGE = "Update Successfully.";
    public static final String DELETE_MESSAGE = "Delete Successfully.";
    public static final String NOT_FOUND = "Person not found!";
    public static final String ALREADY_EXIST = "Already Exists!";

    public static final String FIRST_NAME_NOT_DEFINED = "First name not defined!";
    public static final String LAST_NAME_NOT_DEFINED = "Last name not defined!";

    public static final int BAD_REQUEST = HttpStatus.BAD_REQUEST.value();
}
