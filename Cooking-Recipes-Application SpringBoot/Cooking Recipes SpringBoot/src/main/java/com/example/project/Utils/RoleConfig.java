package com.example.project.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class RoleConfig {

    @Autowired
    private JWTUtil jwtUtility;

    public boolean RoleAuthorization(String pathName){

        String authorization = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        String token = null;
        String roles = null;

        if (null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            roles = jwtUtility.getRoleFromToken(token);
        }

        if(pathName.equalsIgnoreCase("changepassword"))
            return roles.equalsIgnoreCase("USER") || roles.equalsIgnoreCase("ACCOUNTANT") || roles.equalsIgnoreCase("ADMINISTRATOR");
        else if(pathName.equalsIgnoreCase("payments/addPayment"))
            return roles.equalsIgnoreCase("ACCOUNTANT");
        else if(pathName.equalsIgnoreCase("payments/updatePayment"))
            return roles.equalsIgnoreCase("ACCOUNTANT");
        else if(pathName.equalsIgnoreCase("payments/getPayment"))
            return roles.equalsIgnoreCase("USER") || roles.equalsIgnoreCase("ACCOUNTANT");
        else if(pathName.equalsIgnoreCase("user/updateUser"))
            return roles.equalsIgnoreCase("ADMINISTRATOR");
        else if(pathName.equalsIgnoreCase("user/deleteUser"))
            return roles.equalsIgnoreCase("ADMINISTRATOR");
        else if(pathName.equalsIgnoreCase("user/getUser"))
            return roles.equalsIgnoreCase("ADMINISTRATOR");

        return false;

    }

}
