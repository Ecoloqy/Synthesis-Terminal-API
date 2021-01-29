package eu.ecct.synthesysterminal.account;

import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.common.controller.RestApiControllerOperations;

import java.util.UUID;

interface AccountControllerOperations extends RestApiControllerOperations<Account, UUID> {
}
