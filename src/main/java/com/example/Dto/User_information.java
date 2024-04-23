package com.example.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User_information {
    private String USER_NAME;
    private String USER_ID;
    private String USER_PASSWORD;
    private String FACE_INFORMATION;
    private String FINGER_INFORMATION;
    private int ADMIN_AUTORITY;
    private String DOOR_ID;
    private String DOOR_NUMBER;
    private String DOOR_UNION;
    private String AUTHORIZATION_TOKEN;

}