package com.example.userapi.exception;

import javax.security.auth.login.FailedLoginException;

public enum ExceptionMessage {
    User_Not_Found("user not found with this id"),
    Login_Faild("Wrong Username Or Password"),
    User_Is_Not_Active("Account is Deactivated"),
    User_Is_Not_Admin("Restricted Operation"),
    Name_Already_Exist("Name is already used");
    String message;
    ExceptionMessage(String message){
        this.message=message;
    }
}
