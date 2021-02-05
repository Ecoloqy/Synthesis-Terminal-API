package eu.ecct.server.arduino.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArduinoCredentials implements Serializable {

    private Long address;
    private String password;

    public ArduinoCredentials(Long address, String password) {
        this.address = address;
        this.password = password;
    }

}
