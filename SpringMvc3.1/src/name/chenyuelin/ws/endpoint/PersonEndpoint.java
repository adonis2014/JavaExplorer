package name.chenyuelin.ws.endpoint;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;

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
import org.springframework.ws.soap.SoapBody;
import org.springframework.xml.transform.StringResult;

@Component
@Endpoint
public class PersonEndpoint {
	private static final TransformerFactory DEFAULT_TRANSFORMER_FACTORY=TransformerFactory.newInstance();
	
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
	public PersonDto findPerson(@RequestPayload PersonCommand command, @RequestPayload SAXSource saxSource,SoapBody soapBody) throws Exception{
		Transformer transformer=DEFAULT_TRANSFORMER_FACTORY.newTransformer();
		transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		Result result=new StringResult();
		transformer.transform(saxSource, result);
		System.out.println(result.toString());
		result=new StringResult();
		transformer.transform(soapBody.getPayloadSource(), result);
		System.out.println(result.toString());
		System.out.println(soapBody.getPayloadResult().toString());
		Person p = userService.findPerson(command.getId());
		PersonDto person = PersonTransformer.toPersonDto(p);
		return person;
	}
}
