package com.company.validation;

import com.company.entity.Passanger;

import java.util.function.Function;

public interface PassangerValidator extends Function<Passanger , PassangerValidator.validationResult> {
    enum validationResult {
        SUCCESSFULL ,
        USERNAME_IS_NOT_VALID ,
        PASSWORD_IS_NOT_VALID
    }

    static PassangerValidator isUsernameValid(){
        return passanger -> passanger.getUsername().length() > 4 ? validationResult.SUCCESSFULL : validationResult.USERNAME_IS_NOT_VALID;
    }

    static PassangerValidator isPasswordValid(){
        return passanger -> passanger.getPassword().matches("^(?=.*\\d).{4,8}$") ? validationResult.SUCCESSFULL : validationResult.USERNAME_IS_NOT_VALID;
    }

    default PassangerValidator and(PassangerValidator other){
        return passanger -> {
            PassangerValidator.validationResult result = this.apply(passanger);
            return result.equals(PassangerValidator.validationResult.SUCCESSFULL) ? other.apply(passanger) : result;
        };
    }
}
