package eu.ecct.synthesysterminal.config;

import eu.ecct.synthesysterminal.entity.Account;
import eu.ecct.synthesysterminal.entity.Address;
import eu.ecct.synthesysterminal.config.authorization.entity.ApiKey;
import eu.ecct.synthesysterminal.entity.Device;
import eu.ecct.synthesysterminal.entity.models.Country;
import eu.ecct.synthesysterminal.config.authorization.repository.ApiKeyRepository;
import eu.ecct.synthesysterminal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Controller
public class InitData {

    private final AccountService accountService;
    private final ApiKeyRepository apiKeyRepository;

    @Autowired
    public InitData(AccountService accountService, ApiKeyRepository apiKeyRepository) {
        this.accountService = accountService;
        this.apiKeyRepository = apiKeyRepository;
    }

    @PostConstruct
    public void init() {
        List<Device> devices = createDevices();
        Account account = createAccount(devices);
        accountService.postAccount(account);
        apiKeyRepository.save(new ApiKey("5ef5197d-85a3-470f-9272-4f86d899aef4", "/api"));
    }

    private List<Device> createDevices() {
        List<Device> devices = new LinkedList<>();
        Device device1 = new Device("Device 1", 62657127L, "shqtsgdfjhs");
        Device device2 = new Device("Device 2", 12432122L, "gsaajsiqisj");
        devices.add(device1);
        devices.add(device2);
        return devices;
    }

    private Account createAccount(List<Device> devices) {
        Address address = new Address(Country.POLAND, "Kraków", "ul. taka 16", "00-000");
        return new Account("admin@ecct.eu", Account.Type.ADMIN, "Krystian", "password", 6441, Date.valueOf(LocalDate.now()), address, devices);

    }

}
