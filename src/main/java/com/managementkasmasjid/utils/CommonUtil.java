package com.managementkasmasjid.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    public String hashPwdBcrypt(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public boolean checkPwdBcrypt(String inputPassword, String password){
        return BCrypt.checkpw(inputPassword, password);
    }
}
