package com.example.shady.cafev2server.Common;

import com.example.shady.cafev2server.Model.User;

public class Common {
    public static User currentUser;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final int PICK_IMAGE_REQUEST = 71;

    public static String convertCodeToStatus(String code)
    {
        if(code.equals("0"))
            return "Encomendado";
        else if(code.equals("1"))
            return "A Preparar";
        else
            return "Enviado";
    }

}
