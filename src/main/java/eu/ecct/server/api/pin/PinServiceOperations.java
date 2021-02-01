package eu.ecct.server.api.pin;

import eu.ecct.server.api.common.service.RestApiGetByUUIDServiceOperations;
import eu.ecct.server.api.pin.entity.Pin;

interface PinServiceOperations extends RestApiGetByUUIDServiceOperations<Pin, Long> {
}
