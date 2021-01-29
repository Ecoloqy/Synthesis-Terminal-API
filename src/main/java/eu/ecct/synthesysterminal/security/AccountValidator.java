package eu.ecct.synthesysterminal.security;

import eu.ecct.synthesysterminal.account.AccountService;
import eu.ecct.synthesysterminal.security.entity.RegisterDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AccountValidator implements Validator {

    private final AccountService accountService;

    @Autowired
    public AccountValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterDetails.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterDetails details = (RegisterDetails) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        Pattern pattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(details.getUsername());
        if (match.find()) {
            errors.rejectValue("username", "Username can't contain special characters or blank spaces.");
        }
        if (details.getUsername().length() < 6 || details.getUsername().length() > 32) {
            errors.rejectValue("username", "Username length is not valid.");
        }
        if (accountService.getElementByUsername(details.getUsername()).isPresent()) {
            errors.rejectValue("username", "Username already exists.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (details.getPassword().length() < 8 || details.getPassword().length() > 32) {
            errors.rejectValue("password", "Password length is not valid.");
        }
        if (!details.getPassword().equals(details.getConfirmedPassword())) {
            errors.rejectValue("confirmedPassword", "Passwords doesn't match.");
        }

    }

}
