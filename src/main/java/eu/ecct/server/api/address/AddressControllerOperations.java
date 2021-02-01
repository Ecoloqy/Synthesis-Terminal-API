package eu.ecct.server.api.address;

import eu.ecct.server.api.address.entity.Address;
import eu.ecct.server.api.common.controller.AccountDependentControllerOperations;

interface AddressControllerOperations extends AccountDependentControllerOperations<Address, Long> {
}
