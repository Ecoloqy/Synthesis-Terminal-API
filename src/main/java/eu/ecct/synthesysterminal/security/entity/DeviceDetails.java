package eu.ecct.synthesysterminal.security.entity;

import lombok.Data;

@Data
public class DeviceDetails {

    private Long address;
    private String password;

    public DeviceDetails(Long address, String password) {
        this.address = address;
        this.password = password;
    }

}
