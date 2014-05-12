package name.chenyuelin.ws.endpoint;

import name.chenyuelin.service.UserService;
import name.chenyuelin.webapp.dto.PersonDtoListWrap;
import name.chenyuelin.webapp.transformer.PersonTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Component
@Endpoint
public class PersonEndpoint {
	@Autowired
	private UserService userService;
	
	public PersonEndpoint(){
		System.out.println(123);
	}
	
	@PayloadRoot(localPart="getAllPersonsRequest")
	@ResponsePayload
	public PersonDtoListWrap getAllPersons() throws Exception{
		PersonDtoListWrap warp=new PersonDtoListWrap();
		warp.setPerson(PersonTransformer.toPersonDtoList(userService.getAllPersons()));
		return warp;
	}
}
