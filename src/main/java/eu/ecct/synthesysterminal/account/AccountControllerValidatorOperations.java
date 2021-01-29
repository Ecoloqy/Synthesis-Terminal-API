package eu.ecct.synthesysterminal.account;

import eu.ecct.synthesysterminal.common.controller.validator.RestApiControllerValidatorOperations;
import eu.ecct.synthesysterminal.account.entity.Account;

import java.util.UUID;

interface AccountControllerValidatorOperations extends RestApiControllerValidatorOperations<Account, UUID> {
}
