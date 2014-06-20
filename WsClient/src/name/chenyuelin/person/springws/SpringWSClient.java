package name.chenyuelin.person.springws;

import javax.xml.bind.JAXBElement;

import name.chenyuelin.person.springws.dto.ObjectFactory;
import name.chenyuelin.person.springws.dto.PersonCommand;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

public class SpringWSClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("name/chenyuelin/person/springws/spring-ws-client.xml");
		WebServiceTemplate webServiceTemplate = ac.getBean("defalutWebServiceTemplate", WebServiceTemplate.class);

		ObjectFactory objectFactory=ac.getBean(ObjectFactory.class);
		
		PersonCommand personCommand = objectFactory.createPersonCommand();
		personCommand.setId((byte) 1);

		Object result = webServiceTemplate.marshalSendAndReceive(objectFactory.createFindPersonRequest(personCommand));
		System.out.println(((JAXBElement<?>)result).getValue());

	}

}
