package eu.ecct.synthesysterminal.security.security.register;

import lombok.Data;

@Data
public class RegisterDetails {

    private String username;
    private String password;
    private String confirmedPassword;

    public RegisterDetails(String username, String password, String confirmedPassword) {
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

}
