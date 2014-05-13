package name.chenyuelin.ws.endpoint;

import name.chenyuelin.entity.test.Person;
import name.chenyuelin.service.UserService;
import name.chenyuelin.webapp.dto.PersonDto;
import name.chenyuelin.webapp.dto.PersonDtoListWrap;
import name.chenyuelin.webapp.transformer.PersonTransformer;
import name.chenyuelin.ws.dto.PersonCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Component
@Endpoint
public class PersonEndpoint {
	@Autowired
	private UserService userService;

	/*
	 * @PayloadRoot(localPart="getAllPersonsRequest")
	 * @ResponsePayload
	 */
	public PersonDtoListWrap getAllPersons() throws Exception {
		PersonDtoListWrap warp = new PersonDtoListWrap();
		warp.setPerson(PersonTransformer.toPersonDtoList(userService.getAllPersons()));
		return warp;
	}

	@PayloadRoot(localPart = "findPersonRequest", namespace = "http://www.cyl.org/person/schemas")
	@ResponsePayload
	public PersonDto findPerson(@RequestPayload PersonCommand command) {
		Person p = userService.findPerson(command.getId());
		PersonDto person = PersonTransformer.toPersonDto(p);
		return person;
	}
}
