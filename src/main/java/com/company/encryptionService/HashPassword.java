package com.company.encryptionService;

import org.mindrot.jbcrypt.BCrypt;

public class HashPassword {

    public String genSalt(){
        return BCrypt.gensalt();
    }

    public String hashValue(String password , String salt){
        return BCrypt.hashpw(password , salt);
    }
}
