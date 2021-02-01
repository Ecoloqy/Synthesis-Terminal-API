package eu.ecct.server.api.account;

import eu.ecct.server.api.account.entity.Account;
import eu.ecct.server.api.common.controller.RestApiControllerOperations;

import java.util.UUID;

interface AccountControllerOperations extends RestApiControllerOperations<Account, UUID> {
}
