package name.chenyuelin.person.springws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import name.chenyuelin.person.springws.dto.ObjectFactory;
import name.chenyuelin.person.springws.dto.PersonCommand;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SpringWSClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("name/chenyuelin/person/springws/spring-ws-client.xml");
		
		List<Object> list1 = ac.getBean("list1", ArrayList.class);
		
		List<Object> list2 = ac.getBean("list2", ArrayList.class);
		
		list1.add(1);
		list2.add(2);
		
		WebServiceTemplate webServiceTemplate = ac.getBean("defalutWebServiceTemplate", WebServiceTemplate.class);

		ObjectFactory objectFactory=ac.getBean(ObjectFactory.class);
		
		PersonCommand personCommand = objectFactory.createPersonCommand();
		personCommand.setId((byte) 1);

		Object result = webServiceTemplate.marshalSendAndReceive(objectFactory.createFindPersonRequest(personCommand),new SoapActionCallback("http://www.cyl.org/person/schemas/findPersonRequest"));
		System.out.println(((JAXBElement<?>)result).getValue());

	}

}
