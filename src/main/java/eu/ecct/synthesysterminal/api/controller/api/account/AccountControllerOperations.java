package eu.ecct.synthesysterminal.api.controller.api.account;

import eu.ecct.synthesysterminal.api.controller.api.RestApiControllerOperations;
import eu.ecct.synthesysterminal.api.entity.account.Account;

import java.util.Map;
import java.util.UUID;

public interface AccountControllerOperations extends RestApiControllerOperations<Account, UUID> {

    Map<String, Object> getActiveAccount();

}
